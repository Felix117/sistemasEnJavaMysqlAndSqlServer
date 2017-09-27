/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Sistemas
 */
public class Area extends Modelo {

    private int idArea;
    private String nomArea;

    public Area() {
        con = new ControladorMySQL(getControlador_h(), getControlador_u(), getControlador_p(), getControlador_bd());

        idArea = 0;
        nomArea = "";
//        idcg = 0;
    }

    /**
     *
     * @param host
     * @param user
     * @param passw
     * @param bd
     */
    @Override
    public void setControlador(String host, String user, String passw, String bd) {
        con = new ControladorMySQL(host, user, passw, bd);
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNomArea() {
        return nomArea;
    }

    public void setNomArea(String nomArea) {
        this.nomArea = nomArea;
    }

//    public int getIdcg() {
//        return idcg;
//    }
//
//    public void setIdcg(int idcg) {
//        this.idcg = idcg;
//    }
    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_area" + " WHERE clav_area = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idArea = rows.getInt("clav_area");
        nomArea = rows.getString("nombre");
//        idcg = rows.getInt("idCentroGtos");
        con.CerrarConexion();
    }

    public void buscarNomArea(String nombre) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_area" + " WHERE nombre = '" + nombre + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idArea = rows.getInt("clav_area");
        nomArea = rows.getString("nombre");
//        idcg = rows.getInt("idCentroGtos");

        con.CerrarConexion();
    }

    public void buscarArea(String nombre) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_area" + " WHERE nombre LIKE '" + nombre + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idArea = rows.getInt("clav_area");
        nomArea = rows.getString("nombre");
//        idcg = rows.getInt("idCentroGtos");

        con.CerrarConexion();
    }

    public boolean buscarIgualdadArea(String nombre) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        String sql = "SELECT * FROM tbl_area" + " WHERE nombre LIKE '" + nombre + "%'";//28-10-2014
                String sql = "SELECT * FROM tbl_area WHERE nombre LIKE '" + nombre + "'";

        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        if (rows.next()) {
            rows.beforeFirst();
            rows.next();

            idArea = rows.getInt("clav_area");
            nomArea = rows.getString("nombre");
//        idcg = rows.getInt("idCentroGtos");

            con.CerrarConexion();
            return true;
        } else {
            return false;
        }


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
        datos[0] = "null";
        datos[1] = nomArea;
//        datos[2] = idcg + "";
        //mysql
        con.insertar("F_InsertarArea", datos);
        idArea = con.ultimo_ID("clav_area", "tbl_area");

    }

    @Override
    public void borrar() throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        con.conectar();
        borrar(con);
        con.commit();
        con.CerrarConexion();
    }

    @Override
    public void borrar(Controlador con) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        con.eliminar("PA_EliminarArea", idArea);

        idArea = 0;
        nomArea = "";
//        idcg = 0;
    }

    @Override
    public void actualizar() throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        con.conectar();
        actualizar(con);
        con.commit();
        con.CerrarConexion();
    }

    @Override
    public void actualizar(Controlador con) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String[] datos = new String[2];
        datos[0] = idArea + "";
        datos[1] = nomArea;
//        datos[2] = idcg + "";

        con.actualizar("PA_ActualizarArea", datos);

    }
}
