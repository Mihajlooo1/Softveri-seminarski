/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.igrac;

import domen.Igrac;
import java.util.List;
import so.AbstractSO;

public class SOSearchIgrac extends AbstractSO {

    private List<Igrac> list;

    public List<Igrac> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof String)) {
            throw new Exception("Prosledjeni objekat nije instanca klase String");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Igrac>) (List<?>) dBBroker.search(new Igrac(), (String) entity);
    }

}
