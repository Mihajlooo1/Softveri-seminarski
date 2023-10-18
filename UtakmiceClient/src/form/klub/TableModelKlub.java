/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klub;

import domen.Klub;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelKlub extends AbstractTableModel {

    private List<Klub> list;
    private String[] index = new String[]{"Naziv", "Grad"};

    public TableModelKlub(List<Klub> list) {
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
    public String getColumnName(int i) {
        return index[i];
    }

    @Override
    public int getColumnCount() {
        return index.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Klub klub = list.get(i);
        
        switch (i1) {
            case 0:
                return klub.getNaziv();
            case 1:
                return klub.getGrad();
            default:
                return "";
        }
    }

    public Klub getSelected(int row) {
        return list.get(row);
    }

    public void setList(List<Klub> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new LinkedList<>();
        }
        fireTableDataChanged();
    }

}
