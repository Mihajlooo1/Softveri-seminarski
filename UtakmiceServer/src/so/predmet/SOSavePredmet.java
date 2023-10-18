/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predmet;

import domen.Predmet;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOSavePredmet extends AbstractSO{

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Predmet)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Predmet");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.insert((Predmet)entity);
    }
    
}
