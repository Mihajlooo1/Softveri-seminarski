/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDomainObject implements Serializable{
    public abstract String getQueryForAll();
    public abstract List<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
    public abstract String getQueryForSearch(String parametar);
    public abstract String getQueryForInsert();
    public abstract String getQueryForUpdate();
    public abstract String getQueryForDelete();
    public abstract boolean isAutoIncrement();
    public abstract void setId(int id);
}
