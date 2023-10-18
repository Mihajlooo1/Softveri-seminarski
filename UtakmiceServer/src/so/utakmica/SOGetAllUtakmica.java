/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.utakmica;

import domen.RasporedUtakmica;
import domen.Utakmica;
import java.util.List;
import so.AbstractSO;

public class SOGetAllUtakmica extends AbstractSO {

    private List<Utakmica> list;

    public List<Utakmica> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Utakmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Utakmica");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Utakmica>) (List<?>) dBBroker.getAll((Utakmica) entity);
    }

}
