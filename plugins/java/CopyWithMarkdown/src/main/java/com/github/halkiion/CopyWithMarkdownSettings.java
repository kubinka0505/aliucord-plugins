package com.github.halkiion.plugins;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.aliucord.Utils;
import com.aliucord.api.SettingsAPI;
import com.aliucord.fragments.SettingsPage;
import com.aliucord.utils.DimenUtils;
import com.discord.views.CheckedSetting;
import com.discord.views.RadioManager;
import com.google.android.material.card.MaterialCardView;
import com.lytefast.flexinput.R;

import java.util.List;
import java.util.ArrayList;

public class CopyWithMarkdownSettings extends SettingsPage {
    private final SettingsAPI settings;

    public CopyWithMarkdownSettings(SettingsAPI settings) {
        this.settings = settings;
    }

    @Override
    public void onViewBound(View view) {
        super.onViewBound(view);
        setActionBarTitle("Copy With Markdown");
        Context ctx = view.getContext();

        addCheckedSetting(
            ctx,
            "Separate buttons",
            "Create a separate button for copying with markdown",
            "separateButtons"
        );
    }

    private void addCheckedSetting(Context ctx, String title, String subtext, String key) {
        MaterialCardView card = new MaterialCardView(ctx);
        card.setRadius(DimenUtils.INSTANCE.getDefaultCardRadius());
        card.setCardBackgroundColor(com.discord.utilities.color.ColorCompat.getThemedColor(ctx, R.b.colorBackgroundSecondary));
        card.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        ) {{
            setMargins(0, DimenUtils.INSTANCE.getDefaultPadding(), 0, 0);
        }});

        CheckedSetting setting = Utils.createCheckedSetting(ctx, CheckedSetting.ViewType.SWITCH, title, subtext);
        setting.setChecked(settings.getBool(key, false));
        setting.setOnCheckedListener(checked -> settings.setBool(key, checked));

        card.addView(setting);
        getLinearLayout().addView(card);
    }

    private void addRadioGroup(Context ctx, String title, String subtext, String key, String[] labels, int[] values, int selectedValue) {
        MaterialCardView card = new MaterialCardView(ctx);
        card.setRadius(DimenUtils.INSTANCE.getDefaultCardRadius());
        card.setCardBackgroundColor(com.discord.utilities.color.ColorCompat.getThemedColor(ctx, R.b.colorBackgroundSecondary));
        card.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT
        ) {{
            setMargins(0, DimenUtils.INSTANCE.getDefaultPadding(), 0, 0);
        }});


        android.widget.TextView label = new android.widget.TextView(ctx);
        label.setText(title);
        label.setTextSize(16);
        label.setPadding(DimenUtils.INSTANCE.getDefaultPadding(), DimenUtils.INSTANCE.getDefaultPadding(), 0, 0);
        card.addView(label);


        if (subtext != null && !subtext.isEmpty()) {
            android.widget.TextView sub = new android.widget.TextView(ctx);
            sub.setText(subtext);
            sub.setTextSize(12);
            sub.setPadding(DimenUtils.INSTANCE.getDefaultPadding(), 0, 0, DimenUtils.INSTANCE.getDefaultPadding());
            card.addView(sub);
        }


        List<CheckedSetting> radios = new ArrayList<>();
        for (int i = 0; i < labels.length; i++) {
            CheckedSetting radio = Utils.createCheckedSetting(ctx, CheckedSetting.ViewType.RADIO, labels[i], null);
            radio.setChecked(values[i] == selectedValue);
            int value = values[i];
            radio.e(e -> {
                settings.setInt(key, value);
                for (CheckedSetting r : radios) r.setChecked(false);
                radio.setChecked(true);
            });
            radios.add(radio);
            card.addView(radio);
        }

        getLinearLayout().addView(card);
    }
}