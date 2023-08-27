/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author Jorge
 */
public class FicheroContraseñaIn {

    private File fichero;
    private FileInputStream fis;
    private ObjectInputStream ois;

    private static final int ficheroVacio = 4;

    public FicheroContraseñaIn(String nombre) throws FileNotFoundException, IOException {
        fichero = new File(nombre);
        fis = new FileInputStream(fichero);
        ois = new ObjectInputStream(fis);
    }

    /**
     * Retorna una contraseña leída
     *
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Contraseña leerContraseña() throws IOException, ClassNotFoundException {
        return (Contraseña) ois.readObject();
    }

    /**
     * Rétorna si el fichero esta vacio
     *
     * @return
     */
    public boolean ficheroVacio() {
        return fichero.length() == ficheroVacio;
    }
    

    public void cerrar() throws IOException {
        fis.close();
        ois.close();
    }
}
