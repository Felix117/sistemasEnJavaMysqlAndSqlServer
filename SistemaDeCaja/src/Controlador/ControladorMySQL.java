/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sistemas
 */
public class ControladorMySQL extends Controlador {

    public ControladorMySQL(String pusuario, String ppassword) {
//        super("localhost", pusuario, ppassword, "si_sanver");
//        super(host, pusuario, ppassword, BD);
//                super("localhost", pusuario, ppassword, "modulobitacora");
//        super("192.168.1.2", pusuario, ppassword, "activo_sanver");
//                super("localhost", pusuario, ppassword, "sa_sanver2");
    }

    public ControladorMySQL(String phost, String pusuario, String ppassword, String pBD) {
        super(phost, pusuario, ppassword, pBD);
    }

    @Override
    public void conectar() throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        System.out.println("SQL ---> MySQL");
        
        String url = "jdbc:sqlserver://" + host + ";" + BD;
        System.out.println("SQL ---> Abrir Conexión! " + url + "[" + usuario + "," + password + "]]");
//jdbc:sqlserver://localhost:1433;database=SANVER;user=adminSanver;password=Movilidad2017
        conexion = DriverManager.getConnection(url, usuario, password);
        conexion.setAutoCommit(false);

        System.out.println("SQL ---> Conexión Exitosa");
    }

////    }catch(ClassNotFoundException ex){
////          JOptionPane.showMessageDialog(null, "Fue imposible conectarse al servidor."
////                  , host, JOptionPane.ERROR_MESSAGE);
////          ex.printStackTrace();
////          }
////          catch(SQLException ex){
////              System.out.println("No se pudo establecer la connecion");
////              JOptionPane.showMessageDialog(null, "no se pudo conectar con el servidor "
////                      + "jdbc:mysql://"+host+":3306/"+dataBase, "Error de Conecion", JOptionPane.ERROR_MESSAGE);
////              JOptionPane.showMessageDialog(null, "Fue imposible conectarse al servidor."
////                  + "Porfavor siga los pasos para para ejecutar este programa."
////                  + "\nPrimero: Inicie su servido de base de datos si no esta iniciado.\n"
////                  + "Segundo: En los archivos de este proyecto existe una carpeta\n"
////                  + "con el nombre 'basededato' dentro de esta carpeta existe el script sql"
////                  + "\npara la base de datos importe este script desde su gestor de base\n"
////                  + "de datos(sqlyog,phpmyadmin,etc). si aun si tiene problemas\n"
////                  + "debe crear una base de datos con el nombre 'gventas' y usando esta base de datos"
////                  + "\nimporte el script indicado anteriormente."
////                  + "\nTercero: configura cambie el nombre de usuario y la contraseña\n"
////                  + "con el nombre de usuario y contraseña de su servidor de base de datos."
////                  + "\n en el archivo 'elaprendiz.managerbd.BaseConexion.java'\n"
////                  + "Vuelva a ejecutar este programa", host, JOptionPane.ERROR_MESSAGE);
////              ex.printStackTrace();
////              System.exit(0);
////          }
////          catch(NullPointerException ex){
////              System.out.println("se esta pasando un objeto nulo");
////              ex.printStackTrace();
////          }
    @Override
    public int ultimoID() throws ClassNotFoundException, SQLException {
        //throw new UnsupportedOperationException("Not supported yet.");
        ResultSet rows;
        Statement s = conexion.createStatement();
        rows = s.executeQuery("SELECT LAST_INSERT_ID() AS ID");
        rows.next();

        return rows.getInt("ID");
    }

    @Override
    public int ultimo_ID(String campo, String table) throws ClassNotFoundException, SQLException {
//        throw new UnsupportedOperationException("Not supported yet.");

        ResultSet rows;
        Statement s = conexion.createStatement();
        rows = s.executeQuery("SELECT MAX(" + campo + ") AS ID FROM " + table);

        rows.next();
        return rows.getInt("ID");
    }

    @Override
    public boolean insertarConImagen(String Pid, String Pclave, String Pfoto, String Pusuario, String Ppassword, String PfechaReg) throws SQLException {

        String insert = "INSERT INTO tbl_usuario (clav_personal, foto, usuario, `password`,  fecha_registro) VALUES (?,?,?,md5(?),?)";

        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
            conexion.setAutoCommit(false);
            File file = new File(Pfoto);
            fis = new FileInputStream(file);
            ps = conexion.prepareStatement(insert);
//            ps.setString(1, "null");
            ps.setString(1, Pclave);
            ps.setBinaryStream(2, fis, (int) file.length());
            ps.setString(3, Pusuario);
            ps.setString(4, Ppassword);
            ps.setString(5, PfechaReg);
            ps.executeUpdate();
            conexion.commit();
            return true;
        } catch (Exception ex) {
//            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
//                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
            }
        }
        return false;
    }

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
    @Override
    public boolean insertarAsignacionConImagen(String Pnom, String Pinv, int PclavTA, int PclavAG, int PclavAc, String Pdesc, String Pns, String Pmarca, String Pmodelo, String Pcolor, double Pcosto, String Pnf, String Pfa, String Pfad, String Pcc, String Pcd, int PidP, int Pidsd, String PclavP, int PEst, String Pobs, String PAsig, String Pimagen, String Pcodigo) throws Exception {
        String insert = "INSERT INTO tbl_asignacion (nomenclatura, clav_inventario, clav_tipoactivo, clav_ag, clav_activo, descripcion, num_serie, marca, modelo, color, costo, num_factura, fecha_asig, fecha_adquisicion, cuenta_contable, clav_depto, proveedor, clav_sucdepto, clav_personal, estatus, observacion, asigno, imagen, codigo ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        FileInputStream fis_imagen = null;
        FileInputStream fis_codigo = null;
        PreparedStatement ps = null;
        try {
//            conexion.setAutoCommit(false);

            ps = conexion.prepareStatement(insert);
            ps.setString(1, Pnom);
            ps.setString(2, Pinv);
            ps.setInt(3, PclavTA);
            ps.setInt(4, PclavAG);
            ps.setInt(5, PclavAc);
            ps.setString(6, Pdesc);
            ps.setString(7, Pns);
            ps.setString(8, Pmarca);
            ps.setString(9, Pmodelo);
            ps.setString(10, Pcolor);
            ps.setDouble(11, Pcosto);
            ps.setString(12, Pnf);
            ps.setString(13, Pfa);
            ps.setString(14, Pfad);
            ps.setString(15, Pcc);
            ps.setString(16, Pcd);
            ps.setInt(17, PidP);
            ps.setInt(18, Pidsd);
            ps.setString(19, PclavP);
            ps.setInt(20, PEst);
            ps.setString(21, Pobs);
            ps.setString(22, PAsig);

            if (Pimagen.equals("null")) {
                ps.setString(23, Pimagen);
            } else {
                File file_imagen = new File(Pimagen);
                fis_imagen = new FileInputStream(file_imagen);
                ps.setBinaryStream(23, fis_imagen, (int) file_imagen.length());

            }

            if (Pcodigo.equals("null")) {
                ps.setString(24, Pcodigo);
            } else {
                File file_codigo = new File(Pcodigo);
                fis_codigo = new FileInputStream(file_codigo);
                ps.setBinaryStream(24, fis_codigo, (int) file_codigo.length());

            }

            ps.executeUpdate();
//            conexion.commit(); 

            return true;
        } catch (Exception ex) {
//            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
        } finally {
            try {
                ps.close();
                fis_imagen.close();
                fis_codigo.close();
            } catch (Exception ex) {
//                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
            }
        }
        return false;

    }

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
    @Override
    public boolean insertarSucursalConImagen(int PidT, String PnomSuc, String Pclav_contable, String Pnomenclatura, String Pdireccion, int Pclav_municipio, String Plocalidad, int Pcp, String Ptelefono, String Plogo, String Pencargado) throws SQLException {
        String insert = "INSERT INTO tbl_franquicias (clav_empresa, nombre, clav_contable, nomenclatura,  direccion, clav_municipio, localidad, cp, telefono, logo, encargado) VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
//            conexion.setAutoCommit(false);

            ps = conexion.prepareStatement(insert);
            ps.setInt(1, PidT);
            ps.setString(2, PnomSuc);
            ps.setString(3, Pclav_contable);
            ps.setString(4, Pnomenclatura);
            ps.setString(5, Pdireccion);
            ps.setInt(6, Pclav_municipio);
            ps.setString(7, Plocalidad);
            ps.setInt(8, Pcp);
            ps.setString(9, Ptelefono);

            if (Plogo.equals("null")) {
                ps.setString(10, Plogo);
            } else {
                File file = new File(Plogo);
                fis = new FileInputStream(file);
                ps.setBinaryStream(10, fis, (int) file.length());

            }

            ps.setString(11, Pencargado);

            ps.executeUpdate();
//            conexion.commit();
            return true;
        } catch (Exception ex) {
//            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);

        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
//                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);

            }
        }
        return false;
    }

    @Override
    public boolean actualizarSucursalConImagen(int idSuc, int PidT, String PnomSuc, String Pclav_contable, String Pnomenclatura, String Pdireccion, int Pclav_municipio, String Plocalidad, int Pcp, String Ptelefono, String Plogo, String Pencargado) throws SQLException {
        String update = "UPDATE tbl_franquicias SET clav_empresa=?, nombre=?, clav_contable=?, nomenclatura=?,  direccion=?, clav_municipio=?, localidad=?, cp=?, telefono=?, logo=?, encargado=? WHERE clav_franquicia=" + idSuc;

        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {
//            conexion.setAutoCommit(false);

            ps = conexion.prepareStatement(update);
            ps.setInt(1, PidT);
            ps.setString(2, PnomSuc);
            ps.setString(3, Pclav_contable);
            ps.setString(4, Pnomenclatura);
            ps.setString(5, Pdireccion);
            ps.setInt(6, Pclav_municipio);
            ps.setString(7, Plocalidad);
            ps.setInt(8, Pcp);
            ps.setString(9, Ptelefono);
            if (Plogo.equals("null")) {
                ps.setString(10, Plogo);
            } else {
                File file = new File(Plogo);
                fis = new FileInputStream(file);
                ps.setBinaryStream(10, fis, (int) file.length());

            }
            ps.setString(11, Pencargado);

            ps.executeUpdate();

//            conexion.commit();
            return true;
        } catch (Exception ex) {
//            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
        } finally {
            try {
                ps.close();
                fis.close();
            } catch (Exception ex) {
//                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);

            }
        }
        return false;
    }

    @Override
    public boolean actualizarAsignacionConImagen(int PnumA, String Pnom, String Pinv, int PclavTA, int PclavAG, int PclavAc, String Pdesc, String Pns, String Pmarca, String Pmodelo, String Pcolor, double Pcosto, String Pnf, String Pfa, String Pfad, String Pcc, String Pcd, int PidP, int Pidsd, String PclavP, int PEst, String Pobs, String PAsig, String Pimagen, String Pcodigo) throws Exception {
        String update = "UPDATE tbl_asignacion  SET nomenclatura=?, clav_inventario=?, clav_tipoactivo=?, clav_ag=?, clav_activo=?, descripcion=?, num_serie=?, marca=?, modelo=?, color=?, costo=?, num_factura=?, fecha_asig=?, fecha_adquisicion=?, cuenta_contable=?, clav_depto=?, proveedor=?, clav_sucdepto=?, clav_personal=?, estatus=?, observaciones=?, asigno=?, imagen=?, codigo=? WHERE num_asignacion= " + PnumA + ";";

        FileInputStream fis_imagen = null;
        FileInputStream fis_codigo = null;
        PreparedStatement ps = null;
        try {
//            conexion.setAutoCommit(false);

            ps = conexion.prepareStatement(update);
            ps.setString(1, Pnom);
            ps.setString(2, Pinv);
            ps.setInt(3, PclavTA);
            ps.setInt(4, PclavAG);
            ps.setInt(5, PclavAc);
            ps.setString(6, Pdesc);
            ps.setString(7, Pns);
            ps.setString(8, Pmarca);
            ps.setString(9, Pmodelo);
            ps.setString(10, Pcolor);
            ps.setDouble(11, Pcosto);
            ps.setString(12, Pnf);
            ps.setString(13, Pfa);
            ps.setString(14, Pfad);
            ps.setString(15, Pcc);
            ps.setString(16, Pcd);
            ps.setInt(17, PidP);
            ps.setInt(18, Pidsd);
            ps.setString(19, PclavP);
            ps.setInt(20, PEst);
            ps.setString(21, Pobs);
            ps.setString(22, PAsig);

            if (Pimagen.equals("null")) {
                ps.setString(23, Pimagen);
            } else {
                File file_imagen = new File(Pimagen);
                fis_imagen = new FileInputStream(file_imagen);
                ps.setBinaryStream(23, fis_imagen, (int) file_imagen.length());

            }

            if (Pcodigo.equals("null")) {
                ps.setString(24, Pcodigo);
            } else {
                File file_codigo = new File(Pcodigo);
                fis_codigo = new FileInputStream(file_codigo);
                ps.setBinaryStream(24, fis_codigo, (int) file_codigo.length());

            }

            ps.executeUpdate();
//            conexion.commit();

            return true;
        } catch (Exception ex) {
//            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
        } finally {
            try {
                ps.close();
                fis_imagen.close();
                fis_codigo.close();
            } catch (Exception ex) {
//                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
            }
        }
        return false;

    }

//    public boolean actualizarAsignacionConImagen(String PnumA, double Pcosto, String Pnf,  String Pcc, String Pcd, int PidP, String Pimagen) throws Exception {
//        
//        
//        String update = "UPDATE tbl_asignacion  SET nomenclatura=?, clav_inventario=?, clav_tipoactivo=?, clav_ag=?, clav_activo=?, descripcion=?, num_serie=?, marca=?, modelo=?, color=?, costo=?, num_factura=?, fecha_asig=?, fecha_adquisicion=?, cuenta_contable=?, clav_depto=?, proveedor=?, clav_sucdepto=?, clav_personal=?, estatus=?, observaciones=?, asigno=?, imagen=?, codigo WHERE num_asignacion= " + PnumA + ";";
//
//        FileInputStream fis = null;
//        PreparedStatement ps = null;
//        try {
//            conexion.setAutoCommit(false);
//
//            File file = new File(Pimagen);
//            fis = new FileInputStream(file);
//            ps = conexion.prepareStatement(update);
//
//            ps.setDouble(1, Pcosto);
//            ps.setString(2, Pnf);
//            ps.setString(3, Pcc);
//            ps.setString(4, Pcd);
//            ps.setInt(5, PidP);
//            ps.setBinaryStream(6, fis, (int) file.length());
//
//            ps.executeUpdate();
//            conexion.commit();
//
//            return true;
//        } catch (Exception ex) {
////            Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
//        } finally {
//            try {
//                ps.close();
//                fis.close();
//            } catch (Exception ex) {
////                Logger.getLogger(ControladorMySQL.class.getName()).log(Level.SEVERE, null, ex);
//                JOptionPane.showMessageDialog(null, "Mensaje: " + ex);
//
//            }
//        }
//        return false;
//
//    }
    @Override
    public boolean insertar(String N_Funcion, String[] valores) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;

        sql = "SELECT " + N_Funcion + " (";
        for (int i = 0; i < valores.length; i++) {
            if (valores[i].equals("null")) {
                sql += "null";
            } else if (valores[i].equals("now")) {
                sql += "now()";
            } else if (valores[i].equals("true")) {
                sql += "true";
            } else if (valores[i].equals("false")) {
                sql += "false";
            } else {
                sql += "'" + valores[i] + "'";
            }
            if (i < valores.length - 1) {
                sql += ",";
            }
        }
        sql += ")";
        System.out.println("SQL ---> '" + sql + "'");
        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean actualizar(String N_PA, String[] valores) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;

        sql = "CALL " + N_PA + "(";
        for (int i = 0; i < valores.length; i++) {
//            sql += "'" + valores[i] + "'";

            if (valores[i].equals("null")) {
                sql += "null";
            } else if (valores[i].equals("true")) {
                sql += "true";
            } else if (valores[i].equals("false")) {
                sql += "false";
            } else {
                sql += "'" + valores[i] + "'";
            }
            if (i < valores.length - 1) {
                sql += ",";
            }
        }
        sql += ")";

        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    /**
     *
     * @param N_PA
     * @param Pmes
     * @param Paño
     * @return
     * @throws SQLException
     */
    @Override
    public boolean Ejecutar(String N_PA, int Pmes, int Paño) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;

        sql = "CALL " + N_PA + " (" + Pmes + ", " + Paño + ")";

        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean eliminar(String N_PA, int id) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "CALL " + N_PA + " (" + id + ")";
        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean eliminar(String N_PA, String id) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "CALL " + N_PA + " ('" + id + "')";
        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean Crear_Usuario(String p_usuario, String p_password) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "CREATE USER '" + p_usuario + "'@'%' IDENTIFIED BY '" + p_password + "'; ";
//                + "CREATE USER '" + p_usuario + "'@'%' IDENTIFIED BY '" + p_password + "';";
        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean Dar_PrivilegiosAdmon(String p_usuario, String p_password) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;

//        sql = "GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, GRANT OPTION, REFERENCES, INDEX, ALTER, CREATE TEMPORARY TABLES, LOCK TABLES, CREATE VIEW, SHOW VIEW, CREATE ROUTINE, ALTER ROUTINE, EXECUTE, EVENT, TRIGGER ON " + BD + ".* TO " + p_usuario + " IDENTIFIED BY '" + p_password + "' WITH GRANT OPTION;";
////        sql = "GRANT ALL PRIVILEGES ON " + BD + ".* TO '" + p_usuario + "'@'localhost' IDENTIFIED BY '" + p_password + "' WITH GRANT OPTION;";
        sql = "GRANT ALL PRIVILEGES ON *.* TO '" + p_usuario + "'@'%' IDENTIFIED BY '" + p_password + "' WITH GRANT OPTION;";

//                + "GRANT ALL PRIVILEGES ON " + BD + ".* TO '" + p_usuario + "'@'%' IDENTIFIED BY '" + p_password + "' WITH GRANT OPTION;";
//                        + "FLUSH PRIVILEGES;";
        System.out.println("SQL ---> '" + sql + "'");
        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean Dar_PrivilegioConsulta(String p_usuario, String p_password) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String sql;
        System.out.println("base de datos=  " + BD);
        sql = "GRANT SELECT ON " + BD + ".* TO " + p_usuario + " IDENTIFIED BY '" + p_password + "';";

//        sql = "GRANT SELECT ON " + BD + ".* TO " + p_usuario + " IDENTIFIED BY '" + p_password + "';";
//                + "GRANT SELECT ON " + BD + ".* TO " + p_usuario + "'@'%' IDENTIFIED BY '" + p_password + "';";
        System.out.println("SQL ---> '" + sql + "'");
        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean Eliminar_Usuario(String p_usuario) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String sql;
        sql = "DROP USER " + p_usuario + ";";
//                + "DROP USER " + p_usuario + "'@'%';";

        System.out.println("SQL ---> '" + sql + "'");

        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean RevocarPrivilegiosConsulta(String p_usuario, String p_password) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        String sql;
        sql = "REVOKE SELECT ON " + BD + ".* TO " + p_usuario + " IDENTIFIED BY '" + p_password + "';";
        System.out.println("SQL ---> '" + sql + "'");
        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

    @Override
    public boolean RevocarPrivilegiosAdmon(String p_usuario, String p_password) throws SQLException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String sql;
        sql = "REVOKE ALL PRIVILEGES ON " + BD + ".* TO " + p_usuario + " IDENTIFIED BY '" + p_password + "';";
        System.out.println("SQL ---> '" + sql + "'");
        Statement s = conexion.createStatement();
        return s.execute(sql);
    }

}
