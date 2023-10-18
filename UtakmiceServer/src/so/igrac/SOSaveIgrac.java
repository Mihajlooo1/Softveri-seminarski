/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import java.util.List;
import so.AbstractSO;

public class SOSaveIgrac extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac");
        }

        Igrac igrac = (Igrac) entity;
        List<Igrac> lista = (List<Igrac>)(List<?>) dBBroker.getAll(igrac);
        
        for(Igrac igracIzBaze: lista){
            if(igrac.getBrojDresa() == igracIzBaze.getBrojDresa() && igrac.getKlub().getKlubID() == igracIzBaze.getKlub().getKlubID()){
                throw new Exception("Vec postoji igrac sa prosledjenim brojem dresa koji igra za taj klub.");
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((Igrac) entity);
    }

}
