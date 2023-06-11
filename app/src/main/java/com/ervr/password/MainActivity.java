package com.ervr.password;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.ervr.password.model.PasswordStrength;
import com.ervr.password.presenter.PasswordPresenter;
import com.ervr.password.view.PasswordView;

public class MainActivity extends AppCompatActivity implements PasswordView {

    private EditText editTextPassword;
    private TextView textViewPasswordStrength;

    private PasswordPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = findViewById(R.id.editTextPassword);
        textViewPasswordStrength = findViewById(R.id.textViewPasswordStrength);

        presenter = new PasswordPresenter(this);

        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String password = s.toString();
                presenter.evaluatePasswordStrength(password);
            }
        });
    }

    @Override
    public void showPasswordStrength(PasswordStrength strength) {
        String strengthText = "";

        switch (strength) {
            case DEBIL:
                strengthText = "DEBIL";
                break;
            case MEDIA:
                strengthText = "MEDIA";
                break;
            case FUERTE:
                strengthText = "FUERTE";
                break;
        }

        textViewPasswordStrength.setText(strengthText);
    }
}
