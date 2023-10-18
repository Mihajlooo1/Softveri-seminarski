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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import transfer.Request;
import transfer.Response;
import transfer.Operation;

public class ClientController {

    private static ClientController instance;
    private Socket socket;
    private Administrator ulogovani;

    public ClientController() throws Exception {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("konfiguracija.properties"));
            Properties prop = new Properties();
            prop.load(fileInputStream);
            String serverPortText = prop.getProperty("serverPort");
            int port = Integer.parseInt(serverPortText);
            String serverUrl = prop.getProperty("serverUrl");
            socket = new Socket(serverUrl, port);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new Exception("Greska prilikom povezivanja na server");
        }
    }

    public static ClientController getInstance() throws Exception {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }

    public void setUlogovani(Administrator ulogovani) {
        this.ulogovani = ulogovani;
    }
    
    

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(administrator, Operation.LOGIN);
    }

    public void deleteIgrac(Igrac igrac) throws Exception{
        sendRequest(igrac, Operation.DELETE_IGRAC);
    }
    
    public List<Igrac> getAllIgrac() throws Exception{
        return (List<Igrac>) sendRequest(null, Operation.GET_ALL_IGRAC);
    }
    
    public void saveIgrac(Igrac igrac) throws Exception{
        sendRequest(igrac, Operation.SAVE_IGRAC);
    }
    
    public List<Igrac> searchIgrac(String parametar)throws Exception{
        return (List<Igrac>) sendRequest(parametar, Operation.SEARCH_IGRAC);
    }
    
    public List<Klub> getAllKlub() throws Exception{
        return (List<Klub>) sendRequest(null, Operation.GET_ALL_KLUB);
    }
    
    public void saveKlub(Klub klub) throws Exception{
        sendRequest(klub, Operation.SAVE_KLUB);
    }
    
    public List<Klub> searchKlub(String parametar)throws Exception{
        return (List<Klub>) sendRequest(parametar, Operation.SEARCH_KLUB);
    }
    
    public List<Utakmica> getAllUtakmica() throws Exception{
        return (List<Utakmica>) sendRequest(null, Operation.GET_ALL_UTAKMICA);
    }
    
    public void saveRasporedUtakmica(RasporedUtakmica rasporedUtakmica) throws Exception{
        sendRequest(rasporedUtakmica, Operation.SAVE_RASPORED_UTAKMICA);
    }
    
    public List<Utakmica> searchUtakmica(String parametar) throws Exception{
        return (List<Utakmica>) sendRequest(parametar, Operation.SEARCH_UTAKMICA);
    }
    
    public void editUtakmica(Utakmica utakmica) throws Exception{
        sendRequest(utakmica, Operation.EDIT_UTAKMICA);
    }
    
    public List<Igrac> getAllIgracFromKlub(Klub klub) throws Exception{
        return (List<Igrac>) sendRequest(klub, Operation.VRATI_IGRACE_KLUBA);
    }
    public void SavePredmet(Predmet predmet) throws Exception{
        sendRequest(predmet, Operation.SAVE_PREDMET);
    }

    private Object sendRequest(Object podatak, int operacija) throws Exception {
        Request zahtev = new Request(podatak, operacija);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(zahtev);
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Response odgovor = (Response) ois.readObject();
        if (odgovor.getGreska() != null) {
            throw odgovor.getGreska();
        }
        return odgovor.getPodaci();
    }

}
