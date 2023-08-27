/**
 * Ventana que aparece al iniciarse
 */
package Clases;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Jorge
 */
public class VentanaInicio extends JFrame {

    //Tamaño de la ventana
    private final int alto_MAX = 400;
    private final int ancho_MAX = 500;

    //Contraseña para acceder si es correcta
    private boolean correcto = false;
    //Contraseña
    private String PASSWORD;

    //Componetes
    private JPanel panelBoton;
    private JButton entrar;
    private JPasswordField areaTexto;
    private JLabel panelImg;
    
    private VentanaPrincipal ventPrinc;

    //Constructor
    public VentanaInicio(VentanaPrincipal vp) {
        super("Identificación");
        PASSWORD = vp.getPASSWORD();
        ventPrinc = vp;
        initComponents();
    }

    private void initComponents() {
        //+35 por los botones de la ventana y +5 para las separaciones Horitzontales
        this.setSize(ancho_MAX, alto_MAX);
        this.setLocationRelativeTo(null);

        //Quitamos el Layout para ordenar los paneles
        this.getContentPane().setLayout(null);

        //Cierre con la X de la ventana
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //No ensanchable
        this.setResizable(false);

        //Componentes:
        //Panel Boton
        panelBoton = new JPanel();
        panelBoton.setLayout(new FlowLayout());
        panelBoton.setVisible(true);

        //Boton
        entrar = new JButton("ENTRAR");
        entrar.setBackground(Color.BLACK);
        //Acciones al presionar el botón "Entrar"
        entrar.addActionListener((ActionEvent ae) -> {
            bEntrar();
            cerrarVentana();
        });

        //Area de texto
        areaTexto = new JPasswordField();
        areaTexto.setHorizontalAlignment(JPasswordField.CENTER);
        areaTexto.setEchoChar('*');
        areaTexto.setEditable(true);
        areaTexto.setToolTipText("PASSWORD");
        areaTexto.setVisible(true);

        //Eventos (AREA DE TEXTO) //Al presionar ENTER
        areaTexto.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //Al presionar la tencla ENTER
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    bEntrar();
                    cerrarVentana();
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
            }
        });

        //Imagen
        panelImg = new JLabel();
//        ImageIcon imagen = new ImageIcon("Imagenes/Candado.png");
        ImageIcon imagen = new ImageIcon(this.getClass().getResource("/Imagenes/Candado.png"));
        //Escalamos las imagenes, y ademas el escalado es el predeterminado para los gifs
        panelImg.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        panelImg.setVisible(true);

        //.setBounds(x,y,ancho,alto)
        panelImg.setBounds((ancho_MAX / 2) - 100, 50, 200, 200);
        areaTexto.setBounds(2, 285, ancho_MAX - 20, 20);
        panelBoton.setBounds(0, 315, ancho_MAX, 200);
        panelBoton.add(entrar);

        //Añadir componentes
        this.getContentPane().add(panelImg);
        this.getContentPane().add(areaTexto);
        this.getContentPane().add(panelBoton);

    }

    /**
     * Método al pulsar / dar clack en ENTER
     */
    private void bEntrar() {
        String texto = areaTexto.getText();
        if (PASSWORD.equals(texto)) {
            correcto = true;
            ventPrinc.setVisible(correcto);
            //Cerrar ventana
//            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "CONTRASEÑA INCORRECTA", "ERROR", JOptionPane.ERROR_MESSAGE);
            areaTexto.setText("");
        }

    }

    /**
     * Retorna el valor si la contraseña es correcta
     *
     * @return
     */
    public boolean isCorrecto() {
        return correcto;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
    
    public void cerrarVentana(){
        if(correcto){
            this.dispose();
        }else{
            //ERROR
        }
    }
    
}
