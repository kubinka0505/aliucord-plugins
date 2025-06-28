package com.github.halkiion.plugins;

import android.content.Context;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.aliucord.annotations.AliucordPlugin;
import com.aliucord.entities.Plugin;
import com.aliucord.patcher.Hook;
import com.aliucord.Utils;
import com.discord.api.message.attachment.MessageAttachment;
import com.discord.utilities.textprocessing.MessageRenderContext;
import com.discord.widgets.chat.list.adapter.WidgetChatListAdapterItemAttachment;
import com.google.android.material.card.MaterialCardView;

@SuppressWarnings("unused")
@AliucordPlugin
public class CopyFileLink extends Plugin {
    @Override
    public void start(Context context) {
        int attachmentCardId = Utils.getResId("chat_list_item_attachment_card", "id");

        patcher.patch(
            WidgetChatListAdapterItemAttachment.class,
            "configureFileData",
            new Class[]{MessageAttachment.class, MessageRenderContext.class},
            new Hook(callFrame -> {
                var binding = WidgetChatListAdapterItemAttachment.access$getBinding$p((WidgetChatListAdapterItemAttachment) callFrame.thisObject);
                var root = binding.getRoot();

                MaterialCardView card = root.findViewById(attachmentCardId);
                Context ctx = root.getContext();

                MessageAttachment messageAttachment = (MessageAttachment) callFrame.args[0];
                String proxyUrl;
                try {
                    var field = messageAttachment.getClass().getDeclaredField("proxyUrl");
                    field.setAccessible(true);
                    proxyUrl = (String) field.get(messageAttachment);
                } catch (Exception e) {
                    proxyUrl = null;
                }

                if (proxyUrl != null && card != null) {
                    final String url = proxyUrl.replace("://media.discordapp.net/", "://cdn.discordapp.com/");
                    card.setOnLongClickListener(v -> {
                        Utils.setClipboard("Message", url);
                        showToast(ctx, "File link copied to clipboard!", Toast.LENGTH_SHORT);
                        return true;
                    });
                }
            })
        );
    }

    private void showToast(Context ctx, String message, int duration) {
        b.a.d.m.e(ctx, message, duration, null);
    }

    @Override
    public void stop(Context context) {
        patcher.unpatchAll();
    }
}