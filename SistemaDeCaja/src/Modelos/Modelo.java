/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controlador.Controlador;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Sistemas
 */
public abstract class Modelo {

    private String usuario;
    private String password;
    private String host;
    private String bd;
    public static int MYSQL = 1;
    public int N_Registros = 100;
    protected int pagina;
    protected int total_paginas;
    Controlador con;
    Controlador conn;
    Controlador conF;

    public Modelo() {
    }

    public String getControlador_u() {
        return usuario;
    }

    public String getControlador_p() {
        return password;
    }

    public String getControlador_h() {
        return host;
    }

    public String getControlador_bd() {
        return bd;
    }

    public void setControlador(String user, String passw) {
        this.usuario = user;
        this.password = passw;
    }

    public void setControlador(String host, String user, String passw, String bd_) {
        this.host = host;
        this.usuario = user;
        this.password = passw;
        this.bd = bd_;

    }

    public int getPagina() {
        return pagina;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public int getTotal_paginas() {
        return total_paginas;
    }

    public void setTotal_paginas(int total_paginas) {
        this.total_paginas = total_paginas;
    }

    public abstract void buscar(int id) throws ClassNotFoundException, SQLException;

    public abstract ArrayList Listar(String condicion) throws ClassNotFoundException, SQLException;

    public abstract void nuevo() throws ClassNotFoundException, SQLException;

    public abstract void nuevo(Controlador con) throws ClassNotFoundException, SQLException;

    public abstract void borrar() throws ClassNotFoundException, SQLException;

    public abstract void borrar(Controlador con) throws ClassNotFoundException, SQLException;

    public abstract void actualizar() throws ClassNotFoundException, SQLException;

    public abstract void actualizar(Controlador con) throws ClassNotFoundException, SQLException;
}
