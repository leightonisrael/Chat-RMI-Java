import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {
    public void receiveMessage(String name, String message) throws RemoteException;
    public void registerName(String name) throws RemoteException;
}
