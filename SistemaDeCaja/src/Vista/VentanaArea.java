/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelos.Area;
import Controlador.ControladorMySQL;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author JIMENEZ
 */
public class VentanaArea {
    public static void main(String[] args) {
        try {
            Area a = new Area();
            //jdbc:sqlserver://localhost:1433;database=SANVER;user=adminSanver;password=Movilidad2017
            
            ControladorMySQL cm = new ControladorMySQL("localhost:1433", "adminSanver", "Movilidad2017", "database=SANVER;");
            cm.conectar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(VentanaArea.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(VentanaArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
