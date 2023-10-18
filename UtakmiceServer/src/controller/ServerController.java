/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import domen.Administrator;
import domen.Igrac;
import domen.Klub;
import domen.Predmet;
import domen.RasporedUtakmica;
import domen.Utakmica;
import java.util.LinkedList;
import java.util.List;
import so.administrator.SOGetAllAdministratori;
import so.igrac.SODeleteIgrac;
import so.igrac.SOGetAllIgrac;
import so.igrac.SOGetIgracFromKlub;
import so.igrac.SOSaveIgrac;
import so.igrac.SOSearchIgrac;
import so.klub.SOGetAllKlub;
import so.klub.SOSaveKlub;
import so.klub.SOSearchKlub;
import so.predmet.SOSavePredmet;
import so.rasporedutakmica.SOSaveRasporedUtakmica;
import so.utakmica.SOEditUtakmica;
import so.utakmica.SOGetAllUtakmica;
import so.utakmica.SOSearchUtakmica;
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOGetAllAdministratori so = new SOGetAllAdministratori();
        so.templateExecute(new Administrator());
        List<Administrator> list = so.getList();
        for(Administrator a : list)
            if(a.getKorisnickoIme().equals(administrator.getKorisnickoIme()) && a.getSifra().equals(administrator.getSifra()))
                return a;
        throw new Exception("Pogresno uneti kredencijali");
    }

   public void deleteIgrac(Igrac igrac) throws Exception{
       SODeleteIgrac so = new SODeleteIgrac();
       so.templateExecute(igrac);
   }
   
   public List<Igrac> getAllIgrac()throws Exception{
       SOGetAllIgrac so = new SOGetAllIgrac();
       so.templateExecute(new Igrac());
       return so.getList();
   }
   
   public void saveIgrac(Igrac igrac) throws Exception{
       SOSaveIgrac so = new SOSaveIgrac();
       so.templateExecute(igrac);
   }
   
   public List<Igrac> searchIgrac(String parametar) throws Exception{
       SOSearchIgrac so = new SOSearchIgrac();
       so.templateExecute(parametar);
       return so.getList();
   }
   
   public List<Klub> getAllKlub()throws Exception{
       SOGetAllKlub so = new SOGetAllKlub();
       so.templateExecute(new Klub());
       return so.getList();
   }
   
   public void saveKlub(Klub klub) throws Exception{
       SOSaveKlub so = new SOSaveKlub();
       so.templateExecute(klub);
   }
   
   public List<Klub> searchKlub(String parametar) throws Exception{
       SOSearchKlub so = new SOSearchKlub();
       so.templateExecute(parametar);
       return so.getList();
   }
   
   public List<Utakmica> getAllUtakmica()throws Exception{
       SOGetAllUtakmica so = new SOGetAllUtakmica();
       so.templateExecute(new Utakmica());
       return so.getList();
   }
   
   public void saveRasporedUtakmica(RasporedUtakmica rasporedUtakmica) throws Exception{
       SOSaveRasporedUtakmica so = new SOSaveRasporedUtakmica();
       so.templateExecute(rasporedUtakmica);
   }
   
   public List<Utakmica> searchUtakmica(String parametar) throws Exception{
       SOSearchUtakmica so = new SOSearchUtakmica();
       so.templateExecute(parametar);
       return so.getList();
   }
   
   public void editUtakmica(Utakmica utakmica) throws Exception{
       SOEditUtakmica so = new SOEditUtakmica();
       so.templateExecute(utakmica);
   }

    public List<Igrac> vratiSveIgraceKluba(Klub klub) throws Exception {
        
        SOGetIgracFromKlub so = new SOGetIgracFromKlub();
        so.templateExecute(klub);
        return so.getListaIgraca();
    }
    
    public void savePredmet(Predmet predmet) throws Exception{
        SOSavePredmet so = new SOSavePredmet();
       so.templateExecute(predmet);
   }
    
}
