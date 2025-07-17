package com.github.halkiion.plugins;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.inputmethod.InputMethodManager;
import android.view.LayoutInflater;
import android.view.View;

import com.aliucord.Utils;

import com.discord.databinding.ViewDialogConfirmationBinding;

import de.robv.android.xposed.XC_MethodHook;


public class Utility {
    public static void showToast(String message, int duration) {
        new Handler(Looper.getMainLooper()).post(() -> {
            b.a.d.m.e(Utils.getAppContext(), message, duration, null);
        });
    }

    public static void hideKeyboard(View view) {
        if (view == null)
            return;
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showDiscardChangesDialog(Context context, XC_MethodHook.MethodHookParam param) {
        ViewDialogConfirmationBinding binding = ViewDialogConfirmationBinding.b(LayoutInflater.from(context));
        final AlertDialog dialog = new AlertDialog.Builder(context)
                .setView(binding.a)
                .create();

        binding.d.setText(com.lytefast.flexinput.R.h.discard_changes);
        binding.e.setText(com.lytefast.flexinput.R.h.discard_changes_description);

        binding.b.setOnClickListener(view -> dialog.dismiss());
        binding.c.setText(com.lytefast.flexinput.R.h.okay);
        binding.c.setOnClickListener(view -> {
            dialog.dismiss();
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
            param.setResult(true);
        });

        dialog.show();
    }
}