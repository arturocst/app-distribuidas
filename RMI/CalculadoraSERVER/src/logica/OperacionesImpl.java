/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import interfaces.OperacionesRMI;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Carlos
 */
public class OperacionesImpl extends UnicastRemoteObject implements OperacionesRMI {

    public OperacionesImpl() throws RemoteException {
    }
    
    @Override
    public double sumar(double a,double b) throws RemoteException {
                return a + b;

    }

    @Override
    public double restar(double a, double b) throws RemoteException {
                return a - b;

    }

    @Override
    public double multiplicar(double a, double b) throws RemoteException {
                return a * b;

    }

    @Override
    public double dividir(double a, double b) throws RemoteException {
                return a / b;

    }
}
