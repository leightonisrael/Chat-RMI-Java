import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServerInterface extends Remote {
    public void registerChatClient(ChatClientInterface chatClient) throws RemoteException;
    public void broadcastMessage(String name, String message) throws RemoteException;
}
