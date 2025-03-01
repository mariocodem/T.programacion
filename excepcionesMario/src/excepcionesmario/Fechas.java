package excepcionesmario;

import javax.swing.JOptionPane;

/**
 *
 * @author Practica
 */
public class Fechas {
    
    public static void main(String[] args) {
        int dd = 0, mm = 0, aa = 0;
        Fecha f = new Fecha();
        do {
            dd = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite Dia:"));
            mm = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite Mes:"));
            do {
                
                aa = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite Año:"));
                
            } while (aa < 1963);
            
            try {
                f.comprobar(dd, mm, aa);
                
            } catch (ExceptoFecha e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                
            }
            
        } while (dd == 0 && mm == 0 && aa == 0);
        System.exit(0);
    }
}
