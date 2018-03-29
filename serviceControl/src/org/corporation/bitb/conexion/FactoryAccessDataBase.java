
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.corporation.bitb.conexion;

/**
 *
 * @author walter_forero
 */
public class FactoryAccessDataBase {
    
    public static AccessDataBase getAccessDataBase(DataBaseEngine engine) {
        AccessDataBase adb = null;
        switch (engine) {
            case DB2:
                new Exception("No suitable driver found for " + engine + " DataBase");
                break;
            case ORACLE:
                new Exception("No suitable driver found for " + engine + " DataBase");
                break;
            case SQLSERVER:
                new Exception("No suitable driver found for " + engine + " DataBase");
                break;
            case POSTGRESQL:
                adb = new IPostgresqlConnection();
            	new Exception("No suitable driver found for " + engine + " DataBase");
                break;
            case MYSQL:
                //adb = new IMySQLConnection();
            default:
                new Exception("No suitable driver found for " + engine + " DataBase");
                break;
        }
        return adb;

    }
}
