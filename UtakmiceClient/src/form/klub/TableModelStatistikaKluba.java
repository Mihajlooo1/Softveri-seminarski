/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form.klub;

import domen.Klub;
import domen.StatistikaKluba;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelStatistikaKluba extends AbstractTableModel {

    private List<StatistikaKluba> list;
    private String[] index = new String[]{"Broj bodova", "Broj datih golova", "Broj primljenih golova"};

    public TableModelStatistikaKluba(List<StatistikaKluba> list) {
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
        StatistikaKluba statistikaKluba = list.get(i);
        
        switch (i1) {
            case 0:
                return statistikaKluba.getBrojBodova();
            case 1:
                return statistikaKluba.getBrojDatihGolova();
            case 2:
                return statistikaKluba.getBrojPrimljenihGolova();
            default:
                return "";
        }
    }


    public void setList(List<StatistikaKluba> list) {
        this.list = list;
        if (this.list == null) {
            this.list = new LinkedList<>();
        }
        fireTableDataChanged();
    }

    public void addStatistikaKluba(StatistikaKluba statistikaKluba){
        list.add(statistikaKluba);
        fireTableDataChanged();
    }
    
    public void removeStatistikaKluba(int row){
        list.remove(row);
        fireTableDataChanged();
    }

    public List<StatistikaKluba> getList() {
        return list;
    }
    
    
}
