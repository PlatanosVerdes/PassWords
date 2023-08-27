/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Jorge
 */
public class VentanaPrincipal extends JFrame {

    private final int alto_MAX = 590;
    private final int ancho_MAX = 745;

    private static final String nArchivo = "Contraseñas.txt";
    private static final String nArchivoInicio = "PasswordStart.txt";

    private ContraseñaIncio PASSWORD;

    private static final String Instrucciones = "INSTRUCCIONES: \n"
            + "\na) Para GUARDAR una contraseña se ha de insertar:"
            + "\n1. Etiqueta"
            + "\n2. Usuario/Correo electrónico"
            + "\n3. Contraseña"
            + "\n\nb) Para VER o MODIFICAR una constraseña:"
            + "\n- Se debe de insertar la etiqueta correspondiente"
            + "\n\nNOTA: Si se ponen dos etiquetas iguales se"
            + "\nenseñarán todas las contraseñas relacionas con"
            + "\nesa etiqueta. Cabe mencionar intentar no repetir"
            + "\netiquetas.";

    //Componentes:
    //Areas de texto
    //Casillas
    private JTextArea taLabel;
    private JTextArea taUser;
    private JTextArea taPassword;

    //Para enseñar la contraseña
    private JTextArea pantalla;
    private JScrollPane scrollPane;

    //Para insertar texto
    private JTextField tLabel;
    private JTextField tUser;
    private JTextField tPassword;

    //Paneles
    private JPanel panelBotones;
    private JPanel panelContraseñas;
    private JPanel panelBotonVer;
    private JPanel panelBotonCambiarStart;

    //Botones
    private JButton bGuardar;
    private JButton bVer;
    private JButton bVerTodos;
    private JButton bModificar;
    private JButton bDelte;
    private JButton bPassStart;

    //Constructor
    public VentanaPrincipal() {
        super("CONTRASEÑAS");
        PASSWORD = getPass();
        initComponents();
    }

    private void initComponents() {

        this.setSize(ancho_MAX, alto_MAX);
        this.setLocationRelativeTo(null);

        //Quitamos el Layout para ordenar los paneles
        this.getContentPane().setLayout(null);

        //Cierre con la X de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //No ensanchable
        this.setResizable(false);

        //Componentes:
        //Panel de los Botones
        panelBotones = new JPanel();
        //new GridLayout(filas,columnas,espacio entre columnas, espacio entre filas)
        panelBotones.setLayout(new GridLayout(4, 0, 0, 5));
        panelBotones.setVisible(true);

        //Panel de Area de textos (Panel Contraseñas)
        panelContraseñas = new JPanel();
        panelContraseñas.setLayout(new GridLayout(3, 1, 5, 5));
        panelContraseñas.setVisible(true);

        //Panel para el boton de cambiar la contraseña de inicio
        panelBotonCambiarStart = new JPanel();
        panelBotonCambiarStart.setLayout(new FlowLayout());
        panelBotonCambiarStart.setVisible(true);

        //Panel para el boton de "VER TODAS"
        panelBotonVer = new JPanel();
//        panelBotonVer.setLayout(new FlowLayout());
        panelBotonVer.setVisible(true);

        //Botones
        bGuardar = new JButton("GUARDAR");
        bGuardar.setBackground(Color.BLACK);

        bVer = new JButton("VER CONTRASEÑA");
        bVer.setBackground(Color.BLACK);

        bVerTodos = new JButton("  VER TODAS  ");
        bVerTodos.setBackground(Color.BLACK);

        bModificar = new JButton("MODIFICAR CONTRASEÑA");
        bModificar.setBackground(Color.BLACK);

        bDelte = new JButton("BORRAR CONTRASEÑA");
        bDelte.setBackground(Color.BLACK);

        bPassStart = new JButton("Cambiar contraseña Inicio");
        bPassStart.setBackground(Color.BLACK);

        //Acciones al presionar el botón "GUARDAR"
        bGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!casillasVacias()) {
                    bGuardar();
                }
            }
        });

        //Acciones al presionar el botón "VER CONTRASEÑA"
        bVer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!tLabel.getText().equals("")) {
                    bVer();
                } else {
                    pantalla.setText("Escribe la ETIQUETA");
                }

            }
        });

        //Acciones al pulsar "VER TODOS"
        bVerTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //bVerTodo();
                bVerTodoAlfa();
            }
        }
        );

        //Acciones al pulsar "MODIFICAR"
        bModificar.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!casillasVacias()) {
                    bModificar();
                }
            }
        }
        );

        //Acciones al pulsar "BORRAR CONTRASEÑA"
        bDelte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!casillasVacias()) {
                    bDelteC();
                }
            }
        }
        );

        //Acciones al pulsar "CAMBIAR Contraseña inicio"
        bPassStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!tPassword.getText().equals("")) {
                    bPassStart();
                } else {
                    pantalla.setText("Escribe la nueva contraseña de Inicio"
                            + " en la \ncasilla de Contraseña.");
                }
            }
        }
        );

        //Areas de Texto (JTextField)
        tLabel = new JTextField();
        tLabel.setEditable(true);

        tUser = new JTextField();
        tUser.setEditable(true);

        tPassword = new JTextField();
        tPassword.setEditable(true);

        //Areas de texto (JTextArea)
        pantalla = new JTextArea(Instrucciones);
        // sólo va a cambiar el tamaño a 16 puntos
        pantalla.setFont(pantalla.getFont().deriveFont(16f));
        pantalla.setEditable(false);
        pantalla.setVisible(true);

        taLabel = new JTextArea("Etiqueta");
        taLabel.setEditable(false);

        taUser = new JTextArea("Usuario");
        taUser.setEditable(false);

        taPassword = new JTextArea("Contraseña");
        taPassword.setEditable(false);

        //Acccion al pulsar ENTER en la casilla Label        
        tLabel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //Al presionar la tencla ENTER
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!tLabel.getText().equals("")) {
                        bVer();
                    } else {
                        pantalla.setText("Escribe la ETIQUETA");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        //Scroll Pane
        scrollPane = new JScrollPane(pantalla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVisible(true);

        //.setBounds(x,y,ancho,alto)
        panelContraseñas.setBounds(10, 10, 500, 100);
        scrollPane.setBounds(10, 125, 500, 420);
        panelBotones.setBounds(500 + 25, 20, 200, 200);
        panelBotonCambiarStart.setBounds(500 + 25, 500, 200, 35);
        panelBotonVer.setBounds(500 + 25, 350, 200, 35);

        //Añadimos botones
        panelBotones.add(bGuardar);
        panelBotones.add(bVer);
        panelBotones.add(bModificar);
        panelBotones.add(bDelte);
//        panelBotones.add(bVerTodos);

        //Añadimos las Areas de texto al panel correspondiente
        panelContraseñas.add(taLabel);
        panelContraseñas.add(tLabel);

        panelContraseñas.add(taUser);
        panelContraseñas.add(tUser);

        panelContraseñas.add(taPassword);
        panelContraseñas.add(tPassword);

        //Añadimos el boton al panel de cambiar la cotraseña de inicio
        panelBotonCambiarStart.add(bPassStart);

        panelBotonVer.add(bVerTodos);

        //Añadir componentes
        this.getContentPane().add(scrollPane);
        this.getContentPane().add(panelBotones);
        this.getContentPane().add(panelContraseñas);
        this.getContentPane().add(panelBotonVer);
        this.getContentPane().add(panelBotonCambiarStart);

    }

    //MÉTODOS DE LA CLASE//
    /**
     * Método al pulsar botón "GUARDAR"
     */
    private void bGuardar() {
        try {
            Contraseña contraseña = guardarContraseña();

            FicheroContraseñaOut fco = new FicheroContraseñaOut(nArchivo);
            fco.escribirContraseña(contraseña);
            fco.cerrar();

            vaciarCasillas();
            pantalla.setText("¡CONTRASEÑA GUARDADA!");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    /**
     * Método al pulsar botón "VER CONTRASEÑA"
     */
    private void bVer() {
        try {
            String label = tLabel.getText();
            boolean encontrado = false;
            int c = 0;
            FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);

            if (!fci.ficheroVacio()) {
                Contraseña contraseña = fci.leerContraseña();

                //CREAR UN ARCHIVO TEMP?????????????
                //Miramos cuantos elementos hay
                while (!contraseña.isCentinela()) {
                    if (contraseña.isLabel(label)) {
                        c++;
                    }
                    contraseña = fci.leerContraseña();
                }
                fci.cerrar();

                //Los leemos otra vez
                fci = new FicheroContraseñaIn(nArchivo);
                Contraseña[] contraseñas = new Contraseña[c];
                c = 0;
                contraseña = fci.leerContraseña();
                while (!contraseña.isCentinela()) {
                    if (contraseña.isLabel(label)) {
                        contraseñas[c] = contraseña;
                        encontrado = true;
                        c++;
                    }
                    contraseña = fci.leerContraseña();
                }
                fci.cerrar();
                vaciarCasillas();

                if (!encontrado) {
                    pantalla.setText("Registro no encontrado");
                } else {
                    String s = "";
                    for (int i = 0; i < contraseñas.length; i++) {
                        s += contraseñas[i] + "\n";
                    }
                    pantalla.setText(s);
                }

            } else {
                pantalla.setText("Fichero Vacío");
            }
        } catch (IOException ex) {
            pantalla.setText("Todavía no hay ninguna contraseña");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    /**
     * Método al pulsar botón "VER TODAS"
     */
    private void bVerTodo() {
        try {
            String s = "";
            FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);
            if (!fci.ficheroVacio()) {
                Contraseña contraseña = fci.leerContraseña();
                while (!contraseña.isCentinela()) {
                    s += contraseña.toString() + "\n";
                    contraseña = fci.leerContraseña();
                }
                fci.cerrar();
                pantalla.setText(s);
                vaciarCasillas();
            } else {
                pantalla.setText("Fichero Vacío");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            pantalla.setText("Todavía no hay ninguna contraseña");
        }

    }

    /**
     * Método al pulsar botón "VER TODAS" odenadas alfabeticamente
     */
    private void bVerTodoAlfa() {
        try {
            //Contador de contraseñas
            int nContra = 1;
            Contraseña contraseñas[] = new Contraseña[nContra];
            FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);
            if (!fci.ficheroVacio()) {
                Contraseña contraseña = fci.leerContraseña();

                while (!contraseña.isCentinela()) {
                    contraseñas = addArrayC(contraseñas, nContra);
                    contraseñas[nContra - 1] = contraseña;
                    nContra++;
                    contraseña = fci.leerContraseña();
                }
                fci.cerrar();

                //Ordenamos el array
                contraseñas = ordenar(contraseñas);

                //Visualizamos por pantalla
                String c = "";
                for (int i = 0; i < contraseñas.length; i++) {
                    c += contraseñas[i].toString() + "\n";
                }
                pantalla.setText(c);
                pantalla.setCaretPosition(0);
                vaciarCasillas();
            } else {
                pantalla.setText("Fichero Vacío");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            pantalla.setText("Todavía no hay ninguna contraseña");
        }

    }

    /**
     * Método de ordenación de la burbuja
     *
     * @param contraseñas
     * @return
     */
    public Contraseña[] ordenar(Contraseña[] contraseñas) {
        //primer ciclo para recorrer el vector
        for (int i = 0; i < contraseñas.length; i++) {
            //segundo ciclo para recorerr el vector y verificar que el valor de i en el primer ciclo sea diferente al valor de j de este segundo ciclo
            for (int j = 0; j < contraseñas.length && i != j; j++) {
                //comparamos las posiciones de cada ciclo para saber que valor va alfabéticamente antes que el otro
                if (contraseñas[i].getLabelDec().compareToIgnoreCase(contraseñas[j].getLabelDec()) < 0) {
                    //variable auxiliar para guardar el valor de i
                    Contraseña aux = contraseñas[i];
                    //cambio de posiciones
                    contraseñas[i] = contraseñas[j];
                    contraseñas[j] = aux;
                }
            }
        }

        return contraseñas;
    }

    /**
     * Añadir un nuevo elemento y modificar el tamaña en un array
     *
     * @param original
     * @param c
     * @param size
     * @return
     */
    private Contraseña[] addArrayC(Contraseña[] original, int size) {
        Contraseña[] temp = new Contraseña[size];
        for (int i = 0; i < original.length; i++) {
            temp[i] = original[i];
        }
        return temp;
    }

    /**
     * Método al pulsar el botón de "MODIFICAR"
     */
    private void bModificar() {
        try {
            //Contraseña a modificar
            Contraseña cOriginal = guardarContraseña();
            vaciarCasillas();

            FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);

            Contraseña cLeida = fci.leerContraseña();
            Contraseña cModificada = new Contraseña();

            boolean encontrado = false;
            while (!cLeida.isCentinela() && !encontrado) {
                if (cLeida.isEqual(cOriginal)) {
                    String seleccion = JOptionPane.showInputDialog(VentanaPrincipal.this,
                            "Escribe nueva contraseña", "MODIFICAR",
                            JOptionPane.QUESTION_MESSAGE);
                    cModificada = new Contraseña(cOriginal.getLabelDec(), cOriginal.getUserDec(), seleccion);
                    encontrado = true;
                }
                cLeida = fci.leerContraseña();
            }
            fci.cerrar();

            if (encontrado) {
                FicheroContraseñaOut fco = new FicheroContraseñaOut(nArchivo);
                fco.escribirContraseñaModificada(fci, cOriginal, cModificada);
                pantalla.setText("Contraseña modificada");
            } else {
                pantalla.setText("Registro no encontrado");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            pantalla.setText("Todavía no hay ninguna contraseña");
        }
    }

    /**
     * Método para borrar una contraseña
     */
    private void bDelteC() {
        try {
            //Contraseña a eliminar
            Contraseña cDelte = guardarContraseña();
            vaciarCasillas();

            FicheroContraseñaIn fci = new FicheroContraseñaIn(nArchivo);
            boolean encontrado = false;
            boolean borrar = false;

            Contraseña cLeida = fci.leerContraseña();

            while (!cLeida.isCentinela() && !encontrado) {
                if (cLeida.isEqual(cDelte)) {
                    encontrado = true;
                    if (JOptionPane.showConfirmDialog(null, "¿Seguro de que deseas borrar la contraseña?", "WARNING",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        // yes option
                        borrar = true;
                    } else {
                        // no option
                        pantalla.setText("No se ha borrado nada");
                    }
                }
                cLeida = fci.leerContraseña();
            }
            fci.cerrar();

            if (borrar) {
                FicheroContraseñaOut fco = new FicheroContraseñaOut(nArchivo);
                fco.borrarContraseña(fci, cDelte);
                pantalla.setText("¡Contraseña borrada!");
            } else {
                pantalla.setText("Registro no encontrado");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            pantalla.setText("Todavía no hay ninguna contraseña");
        }
    }

    /**
     * Método al pulsar el botón de "BORRAR CONTRASEÑAS"
     */
    private void bDelteAll() {
        if (JOptionPane.showConfirmDialog(null, "Se borrarán TODAS las contraseñas\n¿Deseas continuar?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            // yes option
            try {
                File ficheroContraseñas = new File(nArchivo);
                if (ficheroContraseñas.exists()) {
                    ficheroContraseñas.delete();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
            pantalla.setText("¡Se han borrado TODAS las contraseñas!");
        } else {
            // no option
            pantalla.setText("No se ha borrado nada");
        }
    }

    /**
     * Método al pulsar el botón "CAMBIAR Contraseña inicio"
     */
    private void bPassStart() {
        PASSWORD = new ContraseñaIncio(tPassword.getText());
        try {
            FileOutputStream fos = new FileOutputStream(nArchivoInicio);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(PASSWORD);

            fos.close();
            oos.close();

        } catch (IOException ex) {
            System.out.println(ex);
        }
        vaciarCasillas();
        pantalla.setText("¡Contraseña de Inicio Cambiada!");
    }

    /**
     * Método que verifica si las casillas estan vacias
     *
     * @return
     */
    private boolean casillasVacias() {
        boolean vacias = false;

        if (tLabel.getText().equals("")) {
            pantalla.setText("Escribe la ETIQUETA");
            vacias = true;
        } else if (tUser.getText().equals("")) {
            pantalla.setText("Escribe el USUARIO");
            vacias = true;
        } else if (tPassword.getText().equals("")) {
            pantalla.setText("Escribe la CONTRASEÑA");
            vacias = true;
        }

        return vacias;
    }

    /**
     * Método que lee una contraseña de Inicio, si no esta el archivo se le
     * asigna la de por defecto (123).
     *
     * @return
     */
    private ContraseñaIncio getPass() {
        ContraseñaIncio ci = new ContraseñaIncio("123");
        File fichero = new File(nArchivoInicio);
        try {
            FileInputStream fis = new FileInputStream(fichero);
            ObjectInputStream ois = new ObjectInputStream(fis);

            if (fichero.exists()) {
                ci = (ContraseñaIncio) ois.readObject();
            }

            fis.close();
            ois.close();

        } catch (IOException | ClassNotFoundException ex) {
            return ci;
        }

        return ci;
    }

    /**
     * Método para vaciar las casillas
     */
    private void vaciarCasillas() {
        tLabel.setText("");
        tUser.setText("");
        tPassword.setText("");
    }

    /**
     * Método que crea una Contraseña con los datos de las casillas
     *
     * @return
     */
    private Contraseña guardarContraseña() {
        String label = tLabel.getText();
        String user = tUser.getText();
        String password = tPassword.getText();

        //Ponemos en blanco
        vaciarCasillas();

        return new Contraseña(label, user, password);
    }

    /**
     * Método que develve la contraseña Inicial
     *
     * @return
     */
    public String getPASSWORD() {
        return PASSWORD.getPassword();
    }

}
