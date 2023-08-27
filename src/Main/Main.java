package Main;

import Clases.VentanaInicio;
import Clases.VentanaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author Jorge
 */
public class Main {

    public static void main(String[] args) {
        try {
            //Cambiamos la interfaz a la del sistema con la siguente línea
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //PROGRAMA
        VentanaPrincipal vp = new VentanaPrincipal();
        VentanaInicio vInicio = new VentanaInicio(vp);
        vInicio.setVisible(true);
    }

}
