/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class StatistikaKluba extends AbstractDomainObject{

    private int statistikaKlubaID;
    private Klub klub;
    private int brojBodova;
    private int brojDatihGolova;
    private int brojPrimljenihGolova;

    public StatistikaKluba() {
    }

    public StatistikaKluba(int statistikaKlubaID, Klub klub, int brojBodova, int brojDatihGolova, int brojPrimljenihGolova) {
        this.statistikaKlubaID = statistikaKlubaID;
        this.klub = klub;
        this.brojBodova = brojBodova;
        this.brojDatihGolova = brojDatihGolova;
        this.brojPrimljenihGolova = brojPrimljenihGolova;
    }

    public int getStatistikaKlubaID() {
        return statistikaKlubaID;
    }

    public void setStatistikaKlubaID(int statistikaKlubaID) {
        this.statistikaKlubaID = statistikaKlubaID;
    }

    public Klub getKlub() {
        return klub;
    }

    public void setKlub(Klub klub) {
        this.klub = klub;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public int getBrojDatihGolova() {
        return brojDatihGolova;
    }

    public void setBrojDatihGolova(int brojDatihGolova) {
        this.brojDatihGolova = brojDatihGolova;
    }

    public int getBrojPrimljenihGolova() {
        return brojPrimljenihGolova;
    }

    public void setBrojPrimljenihGolova(int brojPrimljenihGolova) {
        this.brojPrimljenihGolova = brojPrimljenihGolova;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StatistikaKluba other = (StatistikaKluba) obj;
        if (this.statistikaKlubaID != other.statistikaKlubaID) {
            return false;
        }
        return Objects.equals(this.klub, other.klub);
    }
    
    
    @Override
    public String getQueryForAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForSearch(String parametar) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getQueryForInsert() {
        return "Insert into statistikakluba(klubID, brojBodova, brojDatihGolova, brojPrimljenihGolova) values ("+klub.getKlubID()+", "+brojBodova+", "+brojDatihGolova+", "+brojPrimljenihGolova+")";
    }

    @Override
    public String getQueryForUpdate() {
        return "Update statistikakluba set brojBodova="+brojBodova+", brojDatihGolova="+brojDatihGolova+", brojPrimljenihGolova="+brojPrimljenihGolova+" where klubID="+klub.getKlubID()+" and statistikaKlubaID="+statistikaKlubaID;
    }

    @Override
    public String getQueryForDelete() {
        return "Delete from statistikakluba where statistikaKlubaID=" + statistikaKlubaID+" and klubID="+klub.getKlubID();
    }

    @Override
    public boolean isAutoIncrement() {
        return false;
    }

    @Override
    public void setId(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
