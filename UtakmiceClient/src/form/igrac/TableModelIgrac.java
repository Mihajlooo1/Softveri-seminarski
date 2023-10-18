/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.igrac;

import domen.Igrac;
import domen.Klub;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelIgrac extends AbstractTableModel {

    private String[] kolone = new String[]{"Ime", "Prezime", "Pozicija", "Broj dresa", "Klub"};
    private List<Igrac> list;

    public TableModelIgrac(List<Igrac> list) {
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
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Igrac igrac = list.get(i);
        switch (i1) {
            case 0:
                return igrac.getIme();
            case 1:
                return igrac.getPrezime();
            case 2:
                return igrac.getPozicija();
            case 3:
                return igrac.getBrojDresa();
            case 4:
                return igrac.getKlub().getNaziv();
            default:
                return "";
        }
    }

    public Igrac getSelected(int row) {
        return list.get(row);
    }

    public void setList(List<Igrac> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new LinkedList<>();
        }
        fireTableDataChanged();
    }

    public List<Igrac> getList() {
        return list;
    }

    
}
