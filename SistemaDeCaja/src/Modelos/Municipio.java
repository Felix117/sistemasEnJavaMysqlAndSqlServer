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
public class Municipio extends Modelo {

    private int num_registro;
    private String clav_municipio;
    private String descripcion;
    private String clav_estado;

    public Municipio() {
        con = new ControladorMySQL(getControlador_u(), getControlador_p());   con = new ControladorMySQL(getControlador_h(),getControlador_u(), getControlador_p(), getControlador_bd());
        num_registro = 0;
        clav_municipio = "";
        descripcion = "";
        clav_estado = "";
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

    public int getNum_registro() {
        return num_registro;
    }

    public void setNum_registro(int num_registro) {
        this.num_registro = num_registro;
    }

    public String getClav_municipio() {
        return clav_municipio;
    }

    public void setClav_municipio(String clav_municipio) {
        this.clav_municipio = clav_municipio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getClav_estado() {
        return clav_estado;
    }

    public void setClav_estado(String clav_estado) {
        this.clav_estado = clav_estado;
    }

    /**
     *
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_municipios WHERE num_registro = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();
        num_registro = rows.getInt("num_registro");
        clav_municipio = rows.getString("clav_municipio");
        descripcion = rows.getString("descripcion");
        clav_estado = rows.getString("clav_estado");

        con.CerrarConexion();
    }

    public void buscarClavMunicipio(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_municipios WHERE clav_municipio = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();
        num_registro = rows.getInt("num_registro");
        clav_municipio = rows.getString("clav_municipio");
        descripcion = rows.getString("descripcion");
        clav_estado = rows.getString("clav_estado");

        con.CerrarConexion();
    }

    public void buscarPorEdoNombre(String ce, String nom) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_municipios WHERE  clav_estado= '" + ce + "' AND descripcion = '" + nom + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();
        num_registro = rows.getInt("num_registro");
        clav_municipio = rows.getString("clav_municipio");
        descripcion = rows.getString("descripcion");
        clav_estado = rows.getString("clav_estado");

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
        String[] datos = new String[4];
        datos[0] = "null";
        datos[1] = clav_municipio;
        datos[2] = descripcion;
        datos[3] = clav_estado;

        //mysql
        con.insertar("F_InsertarMunicipios", datos);
        num_registro = con.ultimo_ID("num_registro", "tbl_municipios");
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
