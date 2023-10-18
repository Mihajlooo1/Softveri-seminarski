/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.utakmica;

import domen.Utakmica;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelUtakmica extends AbstractTableModel {

    private List<Utakmica> list;
    private String[] index = new String[]{"Rezultat", "Datum", "Stadion", "Kolo", "Domacin", "Gost", "Sezona"};

    public TableModelUtakmica(List<Utakmica> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new LinkedList<>();
        }
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public String getColumnName(int i) {
        return index[i];
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Utakmica utakmica = list.get(i);
        switch (i1) {
            case 0:
                return utakmica.getRezultat();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                return sdf.format(utakmica.getDatum());
            case 2:
                return utakmica.getStadion();
            case 3:
                return utakmica.getKolo();
            case 4:
                return utakmica.getDomacin().getNaziv();
            case 5:
                return utakmica.getGost().getNaziv();
            case 6:
                return utakmica.getRaspored().getSezona();
            default:
                return "";
        }
    }

    public Utakmica getSelected(int i) {
        return list.get(i);
    }

    public void setList(List<Utakmica> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new LinkedList<>();
        }
        fireTableDataChanged();
    }

    public void addUtakmica(Utakmica utakmica) throws Exception{
        for(Utakmica u: list){
            if(u.getKolo() == utakmica.getKolo() && (u.getDomacin().equals(utakmica.getDomacin()) || u.getGost().equals(utakmica.getDomacin()))){
                throw new Exception("Klub:" + utakmica.getDomacin()+ " je vec igrao utakmicu u ovom kolu.");
            }
            if(u.getKolo() == utakmica.getKolo() && (u.getDomacin().equals(utakmica.getGost()) || u.getGost().equals(utakmica.getGost()))){
                throw new Exception("Klub:" + utakmica.getGost()+ " je vec igrao utakmicu u ovom kolu.");
            }
        }
        
        list.add(utakmica);
        fireTableDataChanged();
    }
    
    public void removeUtakmica(int row){
        list.remove(row);
        fireTableDataChanged();
    }

    public List<Utakmica> getList() {
        return list;
    }
    
    public void refreshTable(){
        fireTableDataChanged();
    }

}
