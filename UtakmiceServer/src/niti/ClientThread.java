/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import controller.ServerController;
import domen.Administrator;
import domen.Igrac;
import domen.Klub;
import domen.Predmet;
import domen.RasporedUtakmica;
import domen.Utakmica;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;
import transfer.Request;
import transfer.Response;
import transfer.Operation;

public class ClientThread extends Thread {

    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Request zahtev = (Request) ois.readObject();
                Response odgovor = prihvatiZahtev(zahtev);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(odgovor);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private Response prihvatiZahtev(Request zahtev) {
        Response odgovor = new Response();
        try {
            switch (zahtev.getOperation()) {

                case Operation.LOGIN:
                    odgovor.setPodaci(ServerController.getInstance().login((Administrator) zahtev.getPodaci()));
                    break;
                case Operation.DELETE_IGRAC:
                    ServerController.getInstance().deleteIgrac((Igrac) zahtev.getPodaci());
                    break;
                case Operation.GET_ALL_IGRAC:
                    odgovor.setPodaci(ServerController.getInstance().getAllIgrac());
                    break;
                case Operation.SAVE_IGRAC:
                    ServerController.getInstance().saveIgrac((Igrac)zahtev.getPodaci());
                    break;
                case Operation.SEARCH_IGRAC:
                    odgovor.setPodaci(ServerController.getInstance().searchIgrac((String) zahtev.getPodaci()));
                    break;
                case Operation.GET_ALL_KLUB:
                    odgovor.setPodaci(ServerController.getInstance().getAllKlub());
                    break;
                case Operation.SAVE_KLUB:
                    ServerController.getInstance().saveKlub((Klub) zahtev.getPodaci());
                    break;
                case Operation.SEARCH_KLUB:
                    odgovor.setPodaci(ServerController.getInstance().searchKlub((String)zahtev.getPodaci()));
                    break;
                case Operation.GET_ALL_UTAKMICA:
                    odgovor.setPodaci(ServerController.getInstance().getAllUtakmica());
                    break;
                case Operation.SAVE_RASPORED_UTAKMICA:
                    ServerController.getInstance().saveRasporedUtakmica((RasporedUtakmica) zahtev.getPodaci());
                    break;
                case Operation.SEARCH_UTAKMICA:
                    odgovor.setPodaci(ServerController.getInstance().searchUtakmica((String)zahtev.getPodaci()));
                    break;
                case Operation.EDIT_UTAKMICA:
                    ServerController.getInstance().editUtakmica((Utakmica) zahtev.getPodaci());
                    break;
                case Operation.VRATI_IGRACE_KLUBA:
                    odgovor.setPodaci(ServerController.getInstance().vratiSveIgraceKluba((Klub)zahtev.getPodaci()));
                    break;
                case Operation.SAVE_PREDMET:
                    ServerController.getInstance().savePredmet((Predmet)zahtev.getPodaci());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            odgovor.setGreska(ex);
        }
        return odgovor;
    }

    void zaustaviKlijenta() {
        try {
            this.interrupt();
            socket.close();
        } catch (Exception ex) {

        }
    }

}
