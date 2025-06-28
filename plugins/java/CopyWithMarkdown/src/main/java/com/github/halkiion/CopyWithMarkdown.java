package com.github.halkiion.plugins;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;

import com.aliucord.Utils;
import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.discord.widgets.chat.list.actions.WidgetChatListActions;
import com.lytefast.flexinput.R;

import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AliucordPlugin
public class CopyWithMarkdown extends Plugin {
    private final int buttonId = View.generateViewId();

    public CopyWithMarkdown() {
        settingsTab = new SettingsTab(CopyWithMarkdownSettings.class).withArgs(settings);
    }

    private boolean isMessageCopyable(com.discord.models.message.Message message) {
        return message != null && message.getContent() != null && !message.getContent().isEmpty();
    }

    @Override
    public void start(Context ctx) throws NoSuchMethodException {
        Method configureUIMethod = WidgetChatListActions.class.getDeclaredMethod(
            "configureUI",
            WidgetChatListActions.Model.class
        );
        patcher.patch(configureUIMethod, callFrame -> {
            WidgetChatListActions.Model model = (WidgetChatListActions.Model) callFrame.args[0];
            final com.discord.models.message.Message message = model.getMessage();
            final WidgetChatListActions actions = (WidgetChatListActions) callFrame.thisObject;
            View actionsView = actions.getView();

            if (!(actionsView instanceof NestedScrollView)) return;
            NestedScrollView scrollView = (NestedScrollView) actionsView;
            View child = scrollView.getChildAt(0);
            if (!(child instanceof LinearLayout)) return;
            LinearLayout layout = (LinearLayout) child;

            if (!settings.getBool("separateButtons", false)) {
                ButtonSearchResult btnResult = findCopyTextButton(layout);
                if (btnResult.view != null) {
                    setupMarkdownCopyButton(btnResult.view, message, actions, layout.getContext());
                }
            } else if (isMessageCopyable(message)) {
                View existing = layout.findViewById(buttonId);
                if (existing != null) layout.removeView(existing);

                TextView tv = new TextView(layout.getContext(), null, 0, R.i.UiKit_Settings_Item_Icon);
                tv.setId(buttonId);
                tv.setText(Strings.getString("copy_with_markdown"));
                setupMarkdownCopyButton(tv, message, actions, layout.getContext());
                ButtonSearchResult btnResult = findCopyTextButton(layout);
                if (btnResult.index != -1) {
                    layout.addView(tv, btnResult.index + 1);
                } else {
                    layout.addView(tv);
                }
            }
        });
    }

    private static class ButtonSearchResult {
        public int index;
        public TextView view;
        public ButtonSearchResult(int idx, TextView v) {
            index = idx;
            view = v;
        }
    }

	private ButtonSearchResult findCopyTextButton(LinearLayout layout) {
	    View v = layout.findViewById(Utils.getResId("dialog_chat_actions_copy", "id"));
	    if (v instanceof TextView) {
	        int idx = -1;
	        for (int i = 0; i < layout.getChildCount(); i++) {
	            if (layout.getChildAt(i) == v) {
	                idx = i;
	                break;
	            }
	        }
	        return new ButtonSearchResult(idx, (TextView) v);
	    }
	    return new ButtonSearchResult(-1, null);
	}

    private void setupMarkdownCopyButton(TextView tv, com.discord.models.message.Message message, WidgetChatListActions actions, Context ctx) {
        Drawable copyIcon = ContextCompat.getDrawable(
            ctx,
            R.e.ic_copy_24dp
        );
        if (copyIcon != null) {
            copyIcon.setTint(com.discord.utilities.color.ColorCompat.getThemedColor(
                ctx, R.b.colorInteractiveNormal
            ));
            tv.setCompoundDrawablesRelativeWithIntrinsicBounds(copyIcon, null, null, null);
        }
        tv.setOnClickListener(v2 -> {
            String content = message.getContent();
            if (content == null) content = "";
            Pattern pattern = Pattern.compile("<a?:([a-zA-Z0-9_]+):\\d+>");
            Matcher matcher = pattern.matcher(content);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, ":" + matcher.group(1) + ":");
            }
            matcher.appendTail(sb);
            String markdown = sb.toString();

            Utils.setClipboard("Message", markdown);
            showToast(ctx,
                settings.getBool("separateButtons", false) ? Strings.getString("copied_with_markdown") : Strings.getString("copied_to_clipboard"),
                Toast.LENGTH_LONG);
            actions.dismiss();
        });
    }

    private void showToast(Context ctx, String message, int duration) {
        b.a.d.m.e(ctx, message, duration, null);
    }

    @Override
    public void stop(Context ctx) {
        patcher.unpatchAll();
    }
}