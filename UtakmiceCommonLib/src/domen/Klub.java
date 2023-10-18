/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class Klub extends AbstractDomainObject{

    private int klubID;
    private String naziv;
    private String grad;
    private List<StatistikaKluba> statistikaKluba;
    
    public Klub() {
    }

    public Klub(int klubID, String naziv, String grad, List<StatistikaKluba> statistikaKluba) {
        this.klubID = klubID;
        this.naziv = naziv;
        this.grad = grad;
        this.statistikaKluba = statistikaKluba;
    }
    

    public int getKlubID() {
        return klubID;
    }

    public void setKlubID(int klubID) {
        this.klubID = klubID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public List<StatistikaKluba> getStatistikaKluba() {
        return statistikaKluba;
    }

    public void setStatistikaKluba(List<StatistikaKluba> statistikaKluba) {
        this.statistikaKluba = statistikaKluba;
    }

    @Override
    public String toString() {
        return naziv;
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
        final Klub other = (Klub) obj;
        return this.klubID == other.klubID;
    }
    
    
    
    @Override
    public String getQueryForAll() {
        return "select * from klub inner join statistikakluba on klub.klubID = statistikakluba.klubID";
    }

    @Override
    public List<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        List<AbstractDomainObject> list = new LinkedList<>();
        while(rs.next()){
            int klubID = rs.getInt("klubID");
            String naziv = rs.getString("naziv");
            String grad = rs.getString("grad");
            
            int statistikaKlubaID = rs.getInt("statistikaKlubaID");
            int brojBodova = rs.getInt("brojBodova");
            int brojDatihGolova = rs.getInt("brojDatihGolova");
            int brojPrimljenihGolova = rs.getInt("brojPrimljenihGolova");
            
            
            Klub klub = new Klub(klubID, naziv, grad, new LinkedList<>());
           
            StatistikaKluba statistikaKluba = new StatistikaKluba(statistikaKlubaID, klub, brojBodova, brojDatihGolova, brojPrimljenihGolova);
            
            klub.getStatistikaKluba().add(statistikaKluba);
            
            if(list.contains(klub)){
                int indexOf = list.indexOf(klub);
                Klub klubIzListe = (Klub)list.get(indexOf);
                
                statistikaKluba.setKlub(klubIzListe);
                klubIzListe.getStatistikaKluba().add(statistikaKluba);
            }else{
                list.add(klub);
            }
        }
        rs.close();
        return list;
    }

    @Override
    public String getQueryForSearch(String parametar) {
        return "select * from klub inner join statistikakluba on klub.klubID = statistikakluba.klubID where naziv like '%"+parametar+"%' or grad like '%"+parametar+"%'";
    }
    
    

    @Override
    public String getQueryForInsert() {
        return "Insert into klub(naziv,grad) values ('"+naziv+"', '"+grad+"')";
    }

    @Override
    public String getQueryForUpdate() {
        return "Update klub set naziv='"+naziv+"', grad = '"+grad+"' where klubID = "+klubID;
    }

    @Override
    public String getQueryForDelete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean isAutoIncrement() {
        return true;
    }

    @Override
    public void setId(int id) {
        this.klubID = id;
    }
    
}
