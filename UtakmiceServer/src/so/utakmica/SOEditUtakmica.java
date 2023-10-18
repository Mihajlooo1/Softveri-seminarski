/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.utakmica;

import domen.RasporedUtakmica;
import domen.Utakmica;
import java.util.Date;
import java.util.List;
import so.AbstractSO;

public class SOEditUtakmica extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Utakmica)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Utakmica");
        }

        Utakmica utakmica = (Utakmica) entity;

        List<RasporedUtakmica> list = (List<RasporedUtakmica>) (List<?>) dBBroker.getAll(new RasporedUtakmica());

        for (RasporedUtakmica rasporedUtakmica : list) {
            if (utakmica.getRaspored().getRasporedID() == rasporedUtakmica.getRasporedID()) {
                for (Utakmica u : rasporedUtakmica.getUtakmice()) {
                    if (u.getUtakmicaID()!= utakmica.getUtakmicaID() && u.getKolo() == utakmica.getKolo() && (u.getDomacin().equals(utakmica.getDomacin()) || u.getGost().equals(utakmica.getDomacin()))) {
                        throw new Exception("Klub:" + utakmica.getDomacin() + " je vec igrao utakmicu u ovom kolu.");
                    }
                    if (u.getUtakmicaID()!= utakmica.getUtakmicaID() && u.getKolo() == utakmica.getKolo() && (u.getDomacin().equals(utakmica.getGost()) || u.getGost().equals(utakmica.getGost()))) {
                        throw new Exception("Klub:" + utakmica.getGost() + " je vec igrao utakmicu u ovom kolu.");
                    }
                }
            }
        }
    }

    @Override
    protected void execute(Object entity) throws Exception {
        dBBroker.update((Utakmica) entity);
    }

}
