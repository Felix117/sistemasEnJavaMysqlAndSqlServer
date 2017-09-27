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
public class Departamento extends Modelo{
      private int idDepto;
    private String nomDepto;
 

    public Departamento() {

           con = new ControladorMySQL(getControlador_h(),getControlador_u(), getControlador_p(), getControlador_bd());

        idDepto = 0;
        nomDepto= "";

    }

    @Override
    public void setControlador(String host,String user, String passw, String bd) {
        con = new ControladorMySQL(host,user, passw, bd);
    }

    public int getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(int idDepto) {
        this.idDepto = idDepto;
    }

    public String getNomDepto() {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto) {
        this.nomDepto = nomDepto;
    }

  

   

    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_departamento" + " WHERE clav_dep = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idDepto= rows.getInt("clav_dep");
        nomDepto = rows.getString("nombre");

        con.CerrarConexion();
    }

    public void buscar_nomDepto(String nombre) throws ClassNotFoundException, SQLException {
        // throw new UnsupportedOperationException("Not supported yet.");
        String sql = "SELECT * FROM tbl_departamento" + " WHERE nombre= '" + nombre + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idDepto= rows.getInt("clav_dep");
        nomDepto = rows.getString("nombre");

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
        datos[1] = nomDepto;

        //mysql
        con.insertar("F_InsertarDepartamentos", datos);
        idDepto= con.ultimoID();

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

        con.eliminar("PA_EliminarDepartamento", idDepto);

        idDepto = 0;
        nomDepto= "";
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
        datos[0] = idDepto+ "";
        datos[1] = nomDepto;

        con.actualizar("PA_ActualizarDepartamento", datos);

    }

}
