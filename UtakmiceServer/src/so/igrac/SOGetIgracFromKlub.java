/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import domen.Klub;
import java.util.LinkedList;
import java.util.List;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetIgracFromKlub extends AbstractSO{

    private List<Igrac> listaIgraca;

    public List<Igrac> getListaIgraca() {
        return listaIgraca;
    }
    
    
    @Override
    protected void validate(Object entity) throws Exception {
        if(!(entity instanceof Klub)){
            throw new Exception("Prosledjeni objekat nije klase Klub");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        Klub klub = (Klub)entity;
        List<Igrac> sviIgraci = (List < Igrac >) (List<?>)dBBroker.getAll(new Igrac());
        
        listaIgraca = new LinkedList<>();
        for(Igrac igrac: sviIgraci){
            if(igrac.getKlub().getKlubID() == klub.getKlubID()){
                listaIgraca.add(igrac);
            }
        }
    }
    
}
