/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.Serializable;
import java.util.Base64;

/**
 *
 * @author Jorge
 */
public class ContraseñaIncio implements Serializable {

    private String password;

    public ContraseñaIncio(String p) {
        this.password = coding(p);
    }

    private String coding(String entradaOriginal) {
        return Base64.getEncoder().encodeToString(entradaOriginal.getBytes());

    }

    private String decoding(String cadenaCodificada) {
        return new String(Base64.getDecoder().decode(cadenaCodificada));
    }

    public String getPassword() {
        return decoding(password);
    }

}
