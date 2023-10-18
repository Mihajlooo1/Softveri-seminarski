/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import baza.DBBroker;

public abstract class AbstractSO {

    protected DBBroker dBBroker;

    public AbstractSO() {
        dBBroker = new DBBroker();
    }

    public final void templateExecute(Object entity) throws Exception {
        try {
            startTransaction();
            validate(entity);
            execute(entity);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        dBBroker.connect();
    }

    protected abstract void validate(Object entity) throws Exception;

    protected abstract void execute(Object entity) throws Exception;

    private void commitTransaction() throws Exception {
        dBBroker.commit();
    }

    private void rollbackTransaction() throws Exception {
        dBBroker.rollback();
    }

    private void disconnect() throws Exception {
        dBBroker.disconnect();
    }

}
