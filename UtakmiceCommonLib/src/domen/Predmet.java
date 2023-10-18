/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author PC
 */
public class Predmet extends AbstractDomainObject{
    
    private int predmetID;
    private String naziv;
    private int brojESPB;

    public Predmet() {
    }

    public Predmet(int predmetID, String naziv, int brojESPB) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.brojESPB = brojESPB;
    }

    
    
    

    @Override
    public String getQueryForAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQueryForSearch(String parametar) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQueryForInsert() {
        return "Insert into predmet(naziv,brojESPB) values ('"+naziv+"', '"+brojESPB+"')";
    }

    @Override
    public String getQueryForUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getQueryForDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.predmetID = id;
    }

    public int getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(int predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBrojESPB() {
        return brojESPB;
    }

    public void setBrojESPB(int brojESPB) {
        this.brojESPB = brojESPB;
    }

    
    
}
