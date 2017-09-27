/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controlador.Controlador;
import Controlador.ControladorMySQL;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sistemas
 */
public class Sucursal extends Modelo {

    private int idSucursal;
    private int idTienda;
    private String nomSucursal;
    private String clav_contable;
    private String nomenclatura;
    private String direccion;
    private int clav_municipio;
    private String localidad;
    private int cp;
    private String telefono;
    private String logo;
    private String encargado;
    private Image imgLogo;
    private BufferedImage imagen_bf;

    public Sucursal() {
        con = new ControladorMySQL(getControlador_h(), getControlador_u(), getControlador_p(), getControlador_bd());

        idSucursal = 0;
        idTienda = 0;
        nomSucursal = "";
        clav_contable = "";
        nomenclatura = "";
        direccion = "";
        clav_municipio = 0;
        localidad = "";
        cp = 0;
        telefono = "";
        logo = "";
        encargado = "";
        imgLogo = null;
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

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getNomSucursal() {
        return nomSucursal;
    }

    public void setNomSucursal(String nomSucursal) {
        this.nomSucursal = nomSucursal;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getClav_municipio() {
        return clav_municipio;
    }

    public void setClav_municipio(int clav_municipio) {
        this.clav_municipio = clav_municipio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public String getClav_contable() {
        return clav_contable;
    }

    public void setClav_contable(String clav_contable) {
        this.clav_contable = clav_contable;
    }

    public Image getImgLogo() {
        return imgLogo;
    }

    public void setImgLogo(Image imgLogo) {
        this.imgLogo = imgLogo;
    }

    @Override
    public void buscar(int id) throws ClassNotFoundException, SQLException {
//        thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_franquicias WHERE clav_franquicia = '" + id + "'";
        ResultSet rows;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();
        Blob img = null;
        idSucursal = rows.getInt("clav_franquicia");
        idTienda = rows.getInt("clav_empresa");
        nomSucursal = rows.getString("nombre");
        clav_contable = rows.getString("clav_contable");
        nomenclatura = rows.getString("nomenclatura");
        direccion = rows.getString("direccion");
        clav_municipio = rows.getInt("clav_municipio");
        localidad = rows.getString("localidad");
        cp = rows.getInt("cp");
        telefono = rows.getString("telefono");
        
        img = rows.getBlob("logo");
        if (img != null) {
            try {
                imgLogo = ImageIO.read(img.getBinaryStream());
            } catch (IOException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imgLogo = null;
        }
     
        encargado = rows.getString("encargado");

        con.CerrarConexion();
    }

    public void buscarIdTienda(int idT) throws ClassNotFoundException, SQLException {
//        thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_franquicias WHERE clav_empresa = '" + idT + "'";
        ResultSet rows;

        con.conectar();
        Blob img = null;

        rows = con.Consultar(sql);
        rows.next();

        idSucursal = rows.getInt("clav_franquicia");
        idTienda = rows.getInt("clav_empresa");
        nomSucursal = rows.getString("nombre");
        clav_contable = rows.getString("clav_contable");

        nomenclatura = rows.getString("nomenclatura");
        direccion = rows.getString("direccion");
        clav_municipio = rows.getInt("clav_municipio");
        localidad = rows.getString("localidad");
        cp = rows.getInt("cp");
        telefono = rows.getString("telefono");
        img = rows.getBlob("logo");
        if (img != null) {
            try {
                imgLogo = ImageIO.read(img.getBinaryStream());
            } catch (IOException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imgLogo = null;
        }
        encargado = rows.getString("encargado");

        con.CerrarConexion();
    }

    public void buscarNombreSuc(String nombre) throws ClassNotFoundException, SQLException {
//        thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_franquicias WHERE nombre = '" + nombre + "'";
        ResultSet rows;
      
        Blob img = null;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idSucursal = rows.getInt("clav_franquicia");
        idTienda = rows.getInt("clav_empresa");
        nomSucursal = rows.getString("nombre");
        clav_contable = rows.getString("clav_contable");

        nomenclatura = rows.getString("nomenclatura");
        direccion = rows.getString("direccion");
        clav_municipio = rows.getInt("clav_municipio");
        localidad = rows.getString("localidad");
        cp = rows.getInt("cp");
        telefono = rows.getString("telefono");
//        logo = rows.getString("logo");
        img = rows.getBlob("logo");
        if (img != null) {
            try {
                imgLogo = ImageIO.read(img.getBinaryStream());
            } catch (IOException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imgLogo = null;
        }
        encargado = rows.getString("encargado");

        con.CerrarConexion();
    }

    public void buscarNombreSucLike(String nombre) throws ClassNotFoundException, SQLException {
//        thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql = "SELECT * FROM tbl_franquicias" + " WHERE nombre LIKE '" + nombre + "%'";
        ResultSet rows;
        Blob img = null;

        con.conectar();

        rows = con.Consultar(sql);
        rows.next();

        idSucursal = rows.getInt("clav_franquicia");
        idTienda = rows.getInt("clav_empresa");
        nomSucursal = rows.getString("nombre");
        clav_contable = rows.getString("clav_contable");

        nomenclatura = rows.getString("nomenclatura");
        direccion = rows.getString("direccion");
        clav_municipio = rows.getInt("clav_municipio");
        localidad = rows.getString("localidad");
        cp = rows.getInt("cp");
        telefono = rows.getString("telefono");
       img = rows.getBlob("logo");
        if (img != null) {
            try {
                imgLogo = ImageIO.read(img.getBinaryStream());
            } catch (IOException ex) {
                Logger.getLogger(Sucursal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            imgLogo = null;
        }
        encargado = rows.getString("encargado");

        con.CerrarConexion();
    }

//    public void buscarNomenclatura(int ids) throws ClassNotFoundException, SQLException {
////        thrownew UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        String sql = "SELECT s.clav_franquicia AS clav_franquicia, nom.nomenclatura AS nomenclatura FROM tbl_franquicias AS s INNER JOIN tbl_nomenclatura AS nom ON nom.clav_franquicia = s.clav_franquicia WHERE s.clav_franquicia= '" + ids + "'";
//        ResultSet rows;
//
//        con.conectar();
//
//        rows = con.Consultar(sql);
//        rows.next();
//
//        idSucursal = rows.getInt("clav_franquicia");
//        nomenclatura = rows.getString("nomenclatura");
//     
//
//
//        con.CerrarConexion();
//    }
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

        String datos[] = new String[12];
        datos[0] = "null";
        datos[1] = idTienda + "";
        datos[2] = nomSucursal;
        datos[3] = clav_contable;
        datos[4] = nomenclatura;
        datos[5] = direccion;
        datos[6] = clav_municipio + "";
        datos[7] = localidad;
        datos[8] = cp + "";
        datos[9] = telefono;
        datos[10] = logo;
        datos[11] = encargado;

        if (!logo.equals("null")) {
            System.out.println("===== ENTRA A METODO CON LOGO");
            con.insertarSucursalConImagen(idTienda, nomSucursal, clav_contable, nomenclatura, direccion, clav_municipio, localidad, cp, telefono, logo, encargado);
        } else {
            System.out.println("===== ENTRA A METODO SIN LOGO");
            con.insertar("F_InsertarSucursal", datos);
        }

//      con.insertar("F_InsertarSucursal", datos);
        idSucursal = con.ultimo_ID("clav_franquicia", "tbl_franquicias");
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
        con.eliminar("PA_EliminarSucursal", idSucursal);

        idSucursal = 0;
        idTienda = 0;
        nomSucursal = "";
        clav_contable = "";
        nomenclatura = "";
        direccion = "";
        clav_municipio = 0;
        localidad = "";
        cp = 0;
        telefono = "";
        logo = "";
        encargado = "";

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

        String datos[] = new String[12];
        datos[0] = idSucursal + "";
        datos[1] = idTienda + "";
        datos[2] = nomSucursal;
        datos[3] = clav_contable;
        datos[4] = nomenclatura;
        datos[5] = direccion;
        datos[6] = clav_municipio + "";
        datos[7] = localidad;
        datos[8] = cp + "";
        datos[9] = telefono;
        datos[10] = logo;
        datos[11] = encargado;

        if (!logo.equals("null")) {
            System.out.println("===== ENTRA A METODO CON LOGO");
            con.actualizarSucursalConImagen(idSucursal, idTienda, nomSucursal, clav_contable, nomenclatura, direccion, clav_municipio, localidad, cp, telefono, logo, encargado);
        } else {
            System.out.println("===== ENTRA A METODO SIN LOGO");
            con.actualizar("PA_ActualizarSucursal", datos);
        }

    }
}
