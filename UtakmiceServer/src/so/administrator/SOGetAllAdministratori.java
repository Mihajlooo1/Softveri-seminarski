/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.administrator;

import domen.Administrator;
import java.util.List;
import so.AbstractSO;

public class SOGetAllAdministratori extends AbstractSO {

    private List<Administrator> list;

    public List<Administrator> getList() {
        return list;
    }

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator");
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        list = (List<Administrator>) (List<?>) dBBroker.getAll((Administrator) entity);
    }

}
