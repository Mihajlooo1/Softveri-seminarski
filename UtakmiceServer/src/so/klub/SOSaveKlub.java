/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.klub;

import so.igrac.*;
import domen.Igrac;
import domen.Klub;
import domen.StatistikaKluba;
import java.util.List;
import so.AbstractSO;

public class SOSaveKlub extends AbstractSO {

    @Override
    protected void validate(Object entity) throws Exception {
        if (!(entity instanceof Klub)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klub");
        }

        Klub klub = (Klub) entity;
        List<Klub> lista = (List<Klub>) (List<?>) dBBroker.getAll(klub);

        if (klub.getKlubID() == 0) {
            for (Klub klubIzBaze : lista) {
                if (klub.getNaziv().equalsIgnoreCase(klubIzBaze.getNaziv())) {
                    throw new Exception("Vec postoji klub sa prosledjenim nazivom.");
                }
            }
        } else {
            for (Klub klubIzBaze : lista) {
                if (klub.getNaziv().equalsIgnoreCase(klubIzBaze.getNaziv()) && klub.getKlubID() != klubIzBaze.getKlubID()) {
                    throw new Exception("Vec postoji klub sa prosledjenim nazivom.");
                }
            }
        }

    }

    @Override
    protected void execute(Object entity) throws Exception {
        Klub klub = (Klub) entity;

        if (klub.getKlubID() == 0) {
            dBBroker.insert(klub);

            for (StatistikaKluba statistikaKluba : klub.getStatistikaKluba()) {
                dBBroker.insert(statistikaKluba);
            }
        } else {
            Klub klubIzBaze = null;

            List<Klub> lista = (List<Klub>) (List<?>) dBBroker.getAll(klub);
            for (Klub k : lista) {
                if (k.getKlubID() == klub.getKlubID()) {
                    klubIzBaze = k;
                }
            }

            if (klubIzBaze == null) {
                throw new Exception("Greska. Klub nije pronadjen");
            }

            dBBroker.update(klub);

            for (StatistikaKluba statistikaKluba : klubIzBaze.getStatistikaKluba()) {
                if (!klub.getStatistikaKluba().contains(statistikaKluba)) {
                    dBBroker.delete(statistikaKluba);
                }
            }

            for (StatistikaKluba statistikaKluba : klub.getStatistikaKluba()) {
                if (!klubIzBaze.getStatistikaKluba().contains(statistikaKluba)) {
                    dBBroker.insert(statistikaKluba);
                }
            }
        }

    }

}
