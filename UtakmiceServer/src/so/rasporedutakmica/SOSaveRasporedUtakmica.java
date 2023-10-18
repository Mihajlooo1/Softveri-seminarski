/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.rasporedutakmica;

import domen.RasporedUtakmica;
import domen.Utakmica;
import java.util.List;
import so.AbstractSO;

public class SOSaveRasporedUtakmica extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof RasporedUtakmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase RasporedUtakmicas");
        }
        
        RasporedUtakmica rasporedUtakmica = (RasporedUtakmica) entity;
        List<RasporedUtakmica> lista = (List<RasporedUtakmica>)(List<?>)dBBroker.getAll(rasporedUtakmica);
        for(RasporedUtakmica ru:lista){
            if(ru.getSezona().equalsIgnoreCase(rasporedUtakmica.getSezona())){
                throw new Exception("Vec postoji raspored utakmica sa prosledjenom sezonom.");
            }
        }      
    }

    @Override
    protected void execute(Object entity) throws Exception {
        RasporedUtakmica rasporedUtakmica = (RasporedUtakmica) entity;
        
        dBBroker.insert(rasporedUtakmica);
        
        for(Utakmica utakmica: rasporedUtakmica.getUtakmice()){
            dBBroker.insert(utakmica);
        }
    }

}
