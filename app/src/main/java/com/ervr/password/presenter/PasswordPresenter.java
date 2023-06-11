package com.ervr.password.presenter;


import com.ervr.password.model.PasswordStrength;
import com.ervr.password.view.PasswordView;

public class PasswordPresenter {

    private PasswordView view;

    public PasswordPresenter(PasswordView view) {
        this.view = view;
    }

    public void evaluatePasswordStrength(String password) {
        PasswordStrength strength = calculatePasswordStrength(password);
        view.showPasswordStrength(strength);
    }

    private PasswordStrength calculatePasswordStrength(String password) {
        int length = password.length();

        if (length < 6) {
            return PasswordStrength.DEBIL;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (int i = 0; i < length; i++) {
            char c = password.charAt(i);

            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }

        if (hasUppercase && hasLowercase && hasDigit && hasSpecialChar) {
            return PasswordStrength.FUERTE;
        } else if ((hasUppercase && hasLowercase && hasDigit) || (hasLowercase && hasDigit && hasSpecialChar)) {
            return PasswordStrength.MEDIA;
        } else {
            return PasswordStrength.DEBIL;
        }
    }

}
