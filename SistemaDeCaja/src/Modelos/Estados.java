/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controlador.Controlador;
import Controlador.ControladorMySQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Betty
 */
public class Estados extends Modelo {

    private String clav_estado;
    private String descripcion;

    public Estados() {

          con = new ControladorMySQL(getControlador_h(),getControlador_u(), getControlador_p(), getControlador_bd());

        clav_estado = "";
        descripcion = "";

    }

    /**
     *
     * @param user
     * @param passw
     */
    @Override
    public void setControlador(String host,String user, String passw, String bd) {
        con = new ControladorMySQL(host,user, passw, bd);
    }

    public String getClav_estado() {
        return clav_estado;
    }

    public void setClav_estado(String clav_estado) {
        this.clav_estado = clav_estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void buscar(String id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_estados WHERE clav_estado = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        clav_estado = rows.getString("clav_estado");
        descripcion = rows.getString("descripcion");
        con.CerrarConexion();
    }

    public void buscarNombreEstado(String nom) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_estados WHERE descripcion = '" + nom + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        clav_estado = rows.getString("clav_estado");
        descripcion = rows.getString("descripcion");
        con.CerrarConexion();
    }

    @Override
    public ArrayList Listar(String condicion) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void nuevo() throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        con.conectar();
        nuevo(con);
        con.commit();
        con.CerrarConexion();

    }

    @Override
    public void nuevo(Controlador con) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String[] datos = new String[2];
        datos[0] = clav_estado;
        datos[1] = descripcion;

        //mysql
        con.insertar("F_InsertarEstados", datos);
//                clav_estado = con.ultimo_ID("clav_estado", "tbl_estados");

    }

    @Override
    public void borrar() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrar(Controlador con) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizar(Controlador con) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
