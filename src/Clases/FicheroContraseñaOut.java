/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Jorge
 */
public class FicheroContraseñaOut {

    private String nArchivo;
    private static final String nArchivoTemp = "Temp.txt";
    private static final int ficheroVacio = 4;

    private File fichero;

    private FileOutputStream fos;
    private ObjectOutputStream oos;

    private File fichero_temp;

    private FileOutputStream fos_temp;
    private ObjectOutputStream oos_temp;

    public FicheroContraseñaOut(String n) throws FileNotFoundException, IOException {

        nArchivo = n;

        fichero = new File(nArchivo);
        fos = new FileOutputStream(fichero, true);
        oos = new ObjectOutputStream(fos);

        fichero_temp = new File(nArchivoTemp);
        fos_temp = new FileOutputStream(fichero_temp);
        oos_temp = new ObjectOutputStream(fos_temp);
    }

    /**
     * Método para escribir contraseñas modificadas.
     *
     * @param fci
     * @param contraseñaOriginal
     * @param cM
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void escribirContraseñaModificada(FicheroContraseñaIn fci, Contraseña contraseñaOriginal, Contraseña cM) throws IOException, ClassNotFoundException {
        boolean salir = false;
        fci = new FicheroContraseñaIn(nArchivo);
        Contraseña temp;

        //Leemos el fichero Contraseñas si no esta vacio 
        while ((fichero.length() > ficheroVacio) && (!salir)) {
            temp = fci.leerContraseña();
            //Escribimos las Contraseñas en un archivo temporal (Temp)
            while (!temp.isCentinela()) {
                if (temp.isEqual(contraseñaOriginal)) {
                    //Escribimos la contraseña cambiada
                    oos_temp.writeObject(cM);
                    salir = true;
                } else {
                    oos_temp.writeObject(temp);
                }
                temp = fci.leerContraseña();
            }
            oos_temp.writeObject(Contraseña.CENTINELA);
        }

        fci.cerrar();
        cerrar();
        fichero.delete();
        fichero_temp.renameTo(fichero);
    }

    /**
     * Método para borrar una contraseña
     * @param fci
     * @param c
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void borrarContraseña(FicheroContraseñaIn fci, Contraseña c) throws IOException, ClassNotFoundException {
        boolean salir = false;
        fci = new FicheroContraseñaIn(nArchivo);
        Contraseña temp;

        //Leemos el fichero Contraseñas si no esta vacio 
        while ((fichero.length() > ficheroVacio) && (!salir)) {
            temp = fci.leerContraseña();
            //Escribimos las Contraseñas en un archivo temporal (Temp)
            while (!temp.isCentinela()) {
                if (!temp.isEqual(c)) {
                    oos_temp.writeObject(temp);
                    salir=true;
                }
                temp = fci.leerContraseña();
            }
            oos_temp.writeObject(Contraseña.CENTINELA);
        }

        fci.cerrar();
        cerrar();
        fichero.delete();
        fichero_temp.renameTo(fichero);
    }

    /**
     * Método para escribir contraseña en un fichero
     *
     * @param c
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void escribirContraseña(Contraseña c) throws IOException, ClassNotFoundException {
        FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);
        Contraseña contraseñaTemp;

        //Leemos el fichero Contraseñas si no esta vacio 
        if ((fichero.length() > ficheroVacio)) {
            contraseñaTemp = fci.leerContraseña();
            //Escribimos las Contraseñas en un archivo temporal (Temp)
            while (!contraseñaTemp.isCentinela()) {
                oos_temp.writeObject(contraseñaTemp);
                contraseñaTemp = fci.leerContraseña();
            }
        }

        //Y añadimos la última contraseña a Temp
        oos_temp.writeObject(c);
        oos_temp.writeObject(Contraseña.CENTINELA);

        fci.cerrar();
        cerrar();
        fichero.delete();
        fichero_temp.renameTo(fichero);

    }

    /**
     * Método para cerrar ficheros de la clase
     *
     * @throws IOException
     */
    public void cerrar() throws IOException {
        fos.close();
        oos.close();

        fos_temp.close();
        oos_temp.close();
    }
}
