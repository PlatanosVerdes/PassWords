package Clases;

import java.io.Serializable;
import java.util.Base64;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jorge
 */
public class Contraseña implements Serializable {

    private String label;
    private String user;
    private String password;

    public static final Contraseña CENTINELA = new Contraseña("CENTINELA", "USUARIO", "999");

    public Contraseña(String l, String u, String p) {
        this.label = coding(l);
        this.user = coding(u);
        this.password = coding(p);
    }

    public Contraseña() {
    }

    private String coding(String entradaOriginal) {
        return Base64.getEncoder().encodeToString(entradaOriginal.getBytes());

    }

    private String decoding(String cadenaCodificada) {
        return new String(Base64.getDecoder().decode(cadenaCodificada));
    }

    @Override
    public String toString() {
        return "Etiqueta: " + decoding(label) + "\nUsuario: " + decoding(user)
                + "\nContraseña: " + decoding(password) + "\n";
    }

    /**
     * Método que devuelve si es igual que la etiqueta
     *
     * @param l
     * @return
     */
    public boolean isLabel(String l) {
        return decoding(label).equals(l);
    }

    /**
     * Método que devuelve si dos contraseñas son iguales
     *
     * @param c
     * @return
     */
    public boolean isEqual(Contraseña c) {
        boolean equal = false;
        if (label.equals(c.label)) {
            if (user.equals(c.getUser())) {
                if (password.equals(c.password)) {
                    equal = true;
                }
            }
        }
        return equal;
    }

    /**
     * Método que devuelve si es centinela o no
     *
     * @return
     */
    public boolean isCentinela() {
        return this.label.equals(CENTINELA.label);
    }

    /**
     * Retorna el valor de la etiqueta decodificada
     *
     * @return
     */
    public String getLabelDec() {
        return decoding(label);
    }

    /**
     * Retorna el primer caracter del Label Decodificado
     *
     * @return
     */
    public char getFirstLabelDec() {
        return decoding(label).charAt(1);
    }

    /**
     * Retorna el valor del usuario decodificado
     *
     * @return
     */
    public String getUserDec() {
        return decoding(user);
    }

    public String getLabel() {
        return label;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
