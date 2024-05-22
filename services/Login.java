package services;

import java.util.HashMap;
import java.util.Map;
import classes.Usuario;

public class Login {
    private boolean isLogged = false;
    private Map<String, Usuario> usuarios = new HashMap<>(); 

    public Login() {
        
    }

    public boolean getLogged() {
        return isLogged;
    }

    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
}