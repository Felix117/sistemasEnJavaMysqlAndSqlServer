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
public class Tienda extends Modelo {

    private int idTienda;
    private String nomTienda;


    public Tienda() {

          con = new ControladorMySQL(getControlador_h(),getControlador_u(), getControlador_p(), getControlador_bd());

        idTienda = 0;
        nomTienda = "";

    }

    @Override
     public void setControlador(String host,String user, String passw, String bd) {
        con = new ControladorMySQL(host,user, passw, bd);
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomTienda() {
        return nomTienda;
    }

    public void setNomTienda(String nomTienda) {
        this.nomTienda = nomTienda;
    }

    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_empresa" + " WHERE clav_empresa = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idTienda = rows.getInt("clav_empresa");
        nomTienda = rows.getString("nombre");

        con.CerrarConexion();
    }

    public void buscar_tienda(String nom) throws ClassNotFoundException, SQLException {
        // throw new UnsupportedOperationException("Not supported yet.");
        String sql = "SELECT * FROM tbl_empresa" + " WHERE nombre= '" + nom + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idTienda = rows.getInt("clav_empresa");
        nomTienda = rows.getString("nombre");

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
        datos[0] = "null";
        datos[1] = nomTienda;

        //mysql
        con.insertar("F_InsertarEmpresa", datos);
        idTienda = con.ultimoID();

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

        con.eliminar("PA_EliminarEmpresa", idTienda);

        idTienda = 0;
        nomTienda = "";
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
        datos[0] = idTienda + "";
        datos[1] = nomTienda;

        con.actualizar("PA_ActualizarEmpresa", datos);

    }
}
