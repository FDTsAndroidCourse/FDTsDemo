/*
 * Copyright (c) 2020 fdt <frederic.dt.twh@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package onl.fdt.android.fdtsdemo.ch5;

import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import onl.fdt.android.fdtsdemo.InputListener.InputListener;
import onl.fdt.android.fdtsdemo.R;

public class Ch5Activity extends AppCompatActivity {

    private static final Logger LOGGER = Logger.getLogger(Ch5Activity.class.getName());
    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final String WANANDROID_SIGN_UP_URL = "https://www.wanandroid.com/user/register";

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ch5_activity);

        final EditText username = this.findViewById(R.id.ch4_username);
        final EditText password = this.findViewById(R.id.ch4_pasword);
        final EditText repassword = this.findViewById(R.id.ch4_password_confirm);

        Button button = this.findViewById(R.id.ch4_signup_button);

        InputListener buttonInputListener = new InputListener(button) {
            @Override
            public void onClick(View v) {
                super.onClick(v);

                Ch5Activity.LOGGER.info("button clicked");

                Request signUpRequest = newSignUpRequest(
                        username.getText().toString(),
                        password.getText().toString(),
                        repassword.getText().toString()
                );

                okHttpClient.newCall(signUpRequest)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                                LOGGER.warning("signup post onFailure()");
                                LOGGER.warning(e.getMessage());
                            }

                            @Override
                            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                Looper.prepare();
                                try {
                                    String bodyString = response.body().string();
                                    LOGGER.info(bodyString);
                                    JSONObject json = new JSONObject(bodyString);

                                    if (json.has("errorCode")) {
                                        if (json.getInt("errorCode") == 0) {
                                            toastMessage("Sign up success");
                                        } else {
                                            if (json.has("errorMsg")) {
                                                toastMessage(String.format("Sign up fail: %s", json.getString("errorMsg")));
                                            }
                                        }
                                    }

                                } catch (JSONException e) {
                                    toastMessage(String.format("Sign up fail: %s", e.getMessage()));
                                }
                                Looper.loop();
                            }
                        });
            }
        };

    }

    /**
     * https://www.wanandroid.com/user/register
     * POST api
     *
     * @param username
     * @param password
     * @param repassword
     * @return Request
     */
    public static Request newSignUpRequest(String username, String password, String repassword) {
        RequestBody body = new FormBody.Builder()
                .add("username", username)
                .add("password", password)
                .add("repassword", repassword)
                .build();

        return new Request.Builder().url(WANANDROID_SIGN_UP_URL).post(body).build();
    }

    private void toastMessage(final String message) {
        if (mToast != null) {
            mToast.cancel();
        }

        mToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        mToast.show();
    }


}
