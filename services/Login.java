package services;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import classes.Usuario;

public class Login {
    private static final String EMAIL_REGEX = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static Usuario user;
    private boolean isLogged = false;

    public Login(String usernameOrEmail, String senha, Map<Integer, Usuario> u) {
        u.keySet().forEach(key -> {
            Usuario usuario = u.get(key);
            user = usuario;

            if (isValidEmail(usernameOrEmail) && (senha.equals(usuario.getSenha()) && usernameOrEmail.equals(usuario.getEmail()))) {
                isLogged = true;
            } else if (senha.equals(usuario.getSenha()) && usernameOrEmail.equals(usuario.getUsername())) {
                isLogged = true;
            }
        });
    }

    public static boolean isValidEmail(String email) {
        Matcher match = EMAIL_PATTERN.matcher(email);
        return match.matches();
    }

    public static Usuario getUsuario() {
        return user;
    }

    public boolean getLogged() {
        return isLogged;
    }
}