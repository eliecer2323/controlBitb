/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.corporation.bitb.conexion;

import java.sql.Connection;

/**
 *
 * @author walter_forero
 */
public interface AccessDataBase {

    public Connection getConnection();

    void establishConnection();

    public void closeConnection();

    public boolean testConnection();
}
