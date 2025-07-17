package com.github.halkiion.plugins;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.aliucord.Http;
import com.aliucord.Utils;
import com.discord.utilities.rest.RestAPI;

import java.util.function.Consumer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONObject;


public class APIRequest {

    private static final String AUTH_TOKEN = RestAPI.AppHeadersProvider.INSTANCE.getAuthToken();
    private static final String SUPERPROPS = "eyJvcyI6IldpbmRvd3MiLCJicm93c2VyIjoiRGlzY29yZCBDbGllbnQiLCJyZWxlYXNlX2NoYW5uZWwiOiJzdGFibGUiLCJjbGllbnRfdmVyc2lvbiI6IjEuMC45MTk5Iiwib3NfdmVyc2lvbiI6IjEwLjAuMjI2MzEiLCJvc19hcmNoIjoieDY0IiwiYXBwX2FyY2giOiJ4NjQiLCJzeXN0ZW1fbG9jYWxlIjoiZW4tR0IiLCJoYXNfY2xpZW50X21vZHMiOmZhbHNlLCJjbGllbnRfbGF1bmNoX2lkIjoiOWU2NjliYjYtZDY0MC00MDIwLThiNjktZGIxNTc4NDNjMGUzIiwiYnJvd3Nlcl91c2VyX2FnZW50IjoiTW96aWxsYS81LjAgKFdpbmRvd3MgTlQgMTAuMDsgV2luNjQ7IHg2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgZGlzY29yZC8xLjAuOTE5OSBDaHJvbWUvMTM0LjAuNjk5OC4yMDUgRWxlY3Ryb24vMzUuMy4wIFNhZmFyaS81MzcuMzYiLCJicm93c2VyX3ZlcnNpb24iOiIzNS4zLjAiLCJvc19zZGtfdmVyc2lvbiI6IjIyNjMxIiwiY2xpZW50X2J1aWxkX251bWJlciI6NDE3MjY2LCJuYXRpdmVfYnVpbGRfbnVtYmVyIjo2NTk0MSwiY2xpZW50X2V2ZW50X3NvdXJjZSI6bnVsbCwiY2xpZW50X2hlYXJ0YmVhdF9zZXNzaW9uX2lkIjoiYzU0MDIwZDktYzdjYi00MGU4LWJjZTItZmZiNzQ4NDAwOGEwIiwiY2xpZW50X2FwcF9zdGF0ZSI6ImZvY3VzZWQifQ==";

    public static Map<String, String> buildDefaultHeaders() {
        Map<String, String> headers = new LinkedHashMap<>();
        headers.put("accept", "*/*");
        headers.put("authorization", AUTH_TOKEN);
        headers.put("content-type", "application/json");
        headers.put("origin", "https://discord.com");
        headers.put("priority", "u=1, i");
        headers.put("sec-ch-ua", "\"Not:A-Brand\";v=\"24\", \"Chromium\";v=\"134\"");
        headers.put("sec-ch-ua-mobile", "?0");
        headers.put("sec-ch-ua-platform", "\"Windows\"");
        headers.put("sec-fetch-dest", "empty");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) discord/1.0.9199 Chrome/134.0.6998.205 Electron/35.3.0 Safari/537.36");
        headers.put("x-super-properties", SUPERPROPS);
        return headers;
    }

    public static void sendApiRequest(
            String url,
            String method,
            Map<String, String> headers,
            String rawBody,
            Context context,
            Consumer<String> onSuccess,
            Consumer<Exception> onError,
            CaptchaHandler onCaptcha) {
        new Thread(() -> {
            try {
                Http.Request req = new Http.Request(url, method);
                if (headers != null)
                    headers.forEach(req::setHeader);

                Http.Response res = rawBody == null ? req.execute() : req.executeWithBody(rawBody);
                String responseText = (res != null) ? res.text() : "null response";
                if (onSuccess != null)
                    onSuccess.accept(responseText);
            } catch (Exception e) {
                String responseText = e.getMessage();
                String body = null;
                int idx = responseText == null ? -1 : responseText.indexOf('\n');
                if (idx != -1 && responseText.length() > idx + 1) {
                    body = responseText.substring(idx + 1);
                }
                if (body != null && body.contains("captcha_sitekey") && onCaptcha != null) {
                    try {
                        JSONObject obj = new JSONObject(body);
                        String sitekey = obj.optString("captcha_sitekey", null);
                        String rqdata = obj.optString("captcha_rqdata", null);
                        String rqtoken = obj.optString("captcha_rqtoken", null);
                        String sessionId = obj.optString("captcha_session_id", null);

                        handleCaptcha(context, sitekey, rqdata, rqtoken, sessionId, onCaptcha);
                        return;
                    } catch (Exception parseE) {
                        if (onError != null)
                            onError.accept(parseE);
                        return;
                    }
                }
                if (onError != null)
                    onError.accept(e);
            }
        }).start();
    }

    public interface CaptchaHandler {
        void onCaptchaSolved(String captchaToken, String rqdata, String rqtoken, String sessionId);
    }

    private static void handleCaptcha(
            Context context,
            String sitekey,
            String rqdata,
            String rqtoken,
            String sessionId,
            CaptchaHandler handler) {
        Activity activity = getActivityFromContext(context);
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            activity.runOnUiThread(() -> {
                CaptchaHelper.showCaptchaDialog(activity, sitekey, captchaToken -> {
                    handler.onCaptchaSolved(captchaToken, rqdata, rqtoken, sessionId);
                });
            });
        } else {
            Utility.showToast("No valid Activity to show captcha dialog.", Toast.LENGTH_LONG);
        }
    }

    public static void patchCurrentUser(
            JSONObject payload,
            Context context,
            Consumer<String> onSuccess,
            Consumer<Exception> onError,
            CaptchaHandler onCaptcha) {
        String url = "https://discord.com/api/v9/users/@me";
        String method = "PATCH";
        Map<String, String> headers = buildDefaultHeaders();

        sendApiRequest(
                url,
                method,
                headers,
                payload.toString(),
                context,
                onSuccess,
                onError,
                (captchaToken, rqdata, rqtoken, sessionId) -> {
                    try {
                        payload.put("captcha_key", captchaToken);
                        if (rqdata != null)
                            payload.put("captcha_rqdata", rqdata);
                        if (rqtoken != null)
                            payload.put("captcha_rqtoken", rqtoken);
                        if (sessionId != null)
                            payload.put("captcha_session_id", sessionId);
                    } catch (Exception ignored) {
                    }

                    sendApiRequest(
                            url,
                            method,
                            headers,
                            payload.toString(),
                            context,
                            onSuccess,
                            onError,
                            null);
                });
    }

    public static void setDisplayName(String newDisplayName, Context context, Consumer<Boolean> callback) {
        String field = "global_name";
        JSONObject payload = new JSONObject();
        try {
            payload.put(field, newDisplayName);
        } catch (Exception ignored) {
        }
        patchCurrentUser(
                payload,
                context,
                resp -> {
                    Utility.showToast("Display Name saved.", Toast.LENGTH_LONG);
                    if (callback != null)
                        callback.accept(true);
                },
                e -> {
                    Utility.showToast(parseError(e, "Failed to save Display Name", field), Toast.LENGTH_LONG);
                    if (callback != null)
                        callback.accept(false);
                },
                null);
    }

    public static void setPronouns(String pronouns, Context context) {
        String field = "pronouns";
        JSONObject payload = new JSONObject();
        try {
            payload.put(field, pronouns);
        } catch (Exception ignored) {
        }
        patchCurrentUser(
                payload,
                context,
                resp -> {
                },
                e -> Utility.showToast(parseError(e, "Failed to save Pronouns", field), Toast.LENGTH_LONG),
                null);
    }

    private static Activity getActivityFromContext(Context context) {
        if (context instanceof Activity)
            return (Activity) context;
        try {
            Context base = (Context) context.getClass().getMethod("getBaseContext").invoke(context);
            if (base instanceof Activity)
                return (Activity) base;
        } catch (Exception ignored) {
        }
        return null;
    }

    public static String parseError(Exception e, String fallback, String errField) {
        if (e == null || e.getMessage() == null)
            return fallback;
        try {
            String msg = e.getMessage();
            int jsonStart = msg.indexOf('{');
            if (jsonStart != -1) {
                JSONObject obj = new JSONObject(msg.substring(jsonStart));
                JSONObject err = obj.getJSONObject("errors")
                        .getJSONObject(errField)
                        .getJSONArray("_errors")
                        .getJSONObject(0);
                String errorMsg = err.optString("message", null);
                if (errorMsg != null && !errorMsg.isEmpty())
                    return errorMsg;
            }
        } catch (Exception ignored) {
        }
        return fallback;
    }
}