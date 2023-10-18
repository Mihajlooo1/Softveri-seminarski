/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.igrac;

import domen.Igrac;
import so.AbstractSO;

public class SODeleteIgrac extends AbstractSO{

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Igrac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Igrac");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.delete((Igrac)entity);
    }
    
}
