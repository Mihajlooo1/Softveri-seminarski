/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klub;

import domen.Klub;
import java.util.List;
import so.AbstractSO;

public class SOSearchKlub extends AbstractSO {

    private List<Klub> list;

    public List<Klub> getList() {
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
        list = (List<Klub>) (List<?>) dBBroker.search(new Klub(), (String) entity);
    }

}
