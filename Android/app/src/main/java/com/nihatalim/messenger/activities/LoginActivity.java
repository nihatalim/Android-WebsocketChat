package com.nihatalim.messenger.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.test.mock.MockApplication;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.nihatalim.messenger.R;
import com.nihatalim.messenger.business.interfaces.Connect;
import com.nihatalim.messenger.dto.Request;
import com.nihatalim.messenger.dto.request.LoginRequest;
import com.nihatalim.messenger.dto.response.LoginResponse;
import com.nihatalim.messenger.helpers.App;
import com.nihatalim.messenger.services.SocketService;

public class LoginActivity extends AppCompatActivity {
    private EditText etName;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.etName = findViewById(R.id.etName);
        this.button = findViewById(R.id.button);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Request request = new Request(Request.RequestType.LoginRequest,new LoginRequest(etName.getText().toString()));
                App.service.getInvoker().put("LoginResponse", new Connect<LoginResponse>() {
                    @Override
                    public void run(final LoginResponse response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                App.user = response.getUser();
                                startActivity(new Intent(getContext(), MainActivity.class));
                            }
                        });
                    }
                });
                App.service.getSocket().send(new Gson().toJson(request));
            }
        });
    }

    private Context getContext(){
        return this;
    }
}
