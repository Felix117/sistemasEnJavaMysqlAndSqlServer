/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sistemas
 */
public abstract class Controlador {

    protected String host;
    protected String usuario;
    protected String password;
    protected String BD;
    public Connection conexion;

    public Controlador() {
        host = "localhost:1433";
        usuario = "adminSanver";
        password = "Movilidad2017";
        BD = "database=SANVER";
    }
//jdbc:sqlserver://localhost:1433;database=SANVER;user=adminSanver;password=Movilidad2017
    public Controlador(String phost, String pusuario, String ppassword, String pBD) {
        host = phost;
        usuario = pusuario;
        password = ppassword;
        BD = pBD;
        System.out.println(phost+" aqui");
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBD() {
        return BD;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean conexionAtiva() {
        if (conexion == null) {
            return false;
        } else {
            return true;
        }
    }

    public void CerrarConexion() throws SQLException {
        if (conexionAtiva()) {
            conexion.close();
            conexion = null;
        }
        System.out.println("SQL ---> Cerrar Conexion!");
    }

    public ResultSet Consultar(String consulta) throws SQLException {
        ResultSet fila;
        Statement s = conexion.createStatement();
        fila = s.executeQuery(consulta);
        System.out.println("SQL ---> '" + consulta + "'");
        return fila;
    }

    public abstract void conectar() throws ClassNotFoundException, SQLException;

    public void commit() throws SQLException {
        conexion.commit();
    }

    public void roolback() throws SQLException {
        conexion.rollback();
    }

    public abstract int ultimoID() throws ClassNotFoundException, SQLException;

    public abstract int ultimo_ID(String campo, String table) throws ClassNotFoundException, SQLException;

    /**
     *
     * @param Pid
     * @param Pclave
     * @param Pfoto
     * @param PtipoUsuario
     * @param Pusuario
     * @param Ppassword
     * @param Pestatus
     * @param PfechaReg
     * @return
     * @throws SQLException
     */
    public abstract boolean insertarConImagen(String Pid, String Pnombre, String Pfoto, String Pusuario, String Ppassword, String PfechaReg) throws SQLException;

    /**
     *
     * @param PidT
     * @param PnomSuc
     * @param Pclav_contable
     * @param Pnomenclatura
     * @param Pdireccion
     * @param Pclav_municipio
     * @param Plocalidad
     * @param Pcp
     * @param Ptelefono
     * @param Plogo
     * @param Pencargado
     * @return
     * @throws SQLException
     */
    public abstract boolean insertarSucursalConImagen(int PidT, String PnomSuc, String Pclav_contable, String Pnomenclatura, String Pdireccion, int Pclav_municipio, String Plocalidad, int Pcp, String Ptelefono, String Plogo, String Pencargado) throws SQLException;

    public abstract boolean actualizarSucursalConImagen(int idSuc, int PidT, String PnomSuc, String Pclav_contable, String Pnomenclatura, String Pdireccion, int Pclav_municipio, String Plocalidad, int Pcp, String Ptelefono, String Plogo, String Pencargado) throws SQLException;

    /**
     *
     * @param Pnom
     * @param Pinv
     * @param PclavTA
     * @param PclavAG
     * @param PclavAc
     * @param Pdesc
     * @param Pns
     * @param Pmarca
     * @param Pmodelo
     * @param Pcolor
     * @param Pcosto
     * @param Pnf
     * @param Pfa
     * @param Pfad
     * @param Pcc
     * @param Pcd
     * @param PidP
     * @param Pidsd
     * @param PclavP
     * @param PEst
     * @param Pobs
     * @param PAsig
     * @param Pimagen
     * @param Pcodigo
     * @return
     * @throws Exception
     */
    public abstract boolean insertarAsignacionConImagen(String Pnom, String Pinv, int PclavTA, int PclavAG, int PclavAc, String Pdesc, String Pns, String Pmarca, String Pmodelo, String Pcolor, double Pcosto, String Pnf, String Pfa, String Pfad, String Pcc, String Pcd, int PidP, int Pidsd, String PclavP, int PEst, String Pobs, String PAsig, String Pimagen, String Pcodigo) throws Exception;
//    public abstract boolean actualizarAsignacionConImagen(String PnumA, double Pcosto, String Pnf,  String Pcc, String Pcd, int PidP, String Pimagen) throws Exception;

    /**
     *
     * @param numA
     * @param Pnom
     * @param Pinv
     * @param PclavTA
     * @param PclavAG
     * @param PclavAc
     * @param Pdesc
     * @param Pns
     * @param Pmarca
     * @param Pmodelo
     * @param Pcolor
     * @param Pcosto
     * @param Pnf
     * @param Pfa
     * @param Pfad
     * @param Pcc
     * @param Pcd
     * @param PidP
     * @param Pidsd
     * @param PclavP
     * @param PEst
     * @param Pobs
     * @param PAsig
     * @param Pimagen
     * @param Pcodigo
     * @return
     * @throws Exception
     */
    public abstract boolean actualizarAsignacionConImagen(int numA, String Pnom, String Pinv, int PclavTA, int PclavAG, int PclavAc, String Pdesc, String Pns, String Pmarca, String Pmodelo, String Pcolor, double Pcosto, String Pnf, String Pfa, String Pfad, String Pcc, String Pcd, int PidP, int Pidsd, String PclavP, int PEst, String Pobs, String PAsig, String Pimagen, String Pcodigo) throws Exception;

    public abstract boolean insertar(String N_Funcion, String[] valores) throws SQLException;

    public boolean insertar(String N_Funcion, String[] valores, String extra) throws SQLException {

        if (this.conexionAtiva()) {
            return insertar(N_Funcion, valores, "");
        } else {
            System.err.println("SQL ---> Error: No hay conexión Activa!");
        }
        return false;
    }

    public abstract boolean actualizar(String N_PA, String[] valores) throws SQLException;

    public abstract boolean eliminar(String N_PA, int id) throws SQLException;

    public abstract boolean eliminar(String N_PA, String id) throws SQLException;

    public abstract boolean Ejecutar(String N_PA, int Pmes, int Paño) throws SQLException;

    public abstract boolean Crear_Usuario(String p_usuario, String p_password) throws SQLException;

    public abstract boolean Dar_PrivilegiosAdmon(String p_usuario, String p_password) throws SQLException;

    public abstract boolean Dar_PrivilegioConsulta(String p_usuario, String p_password) throws SQLException;

    public abstract boolean Eliminar_Usuario(String p_usuario) throws SQLException;

    public abstract boolean RevocarPrivilegiosAdmon(String p_usuario, String p_password) throws SQLException;

    public abstract boolean RevocarPrivilegiosConsulta(String p_usuario, String p_password) throws SQLException;
}
