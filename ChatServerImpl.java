import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ChatServerImpl extends UnicastRemoteObject implements ChatServerInterface {
    private static final long serialVersionUID = 1L;
    private ArrayList<ChatClientInterface> chatClients;
    private Map<ChatClientInterface, String> clientNames;

    public ChatServerImpl() throws RemoteException {
        chatClients = new ArrayList<ChatClientInterface>();
        clientNames = new HashMap<ChatClientInterface, String>();
    }

    public void registerChatClient(ChatClientInterface chatClient) throws RemoteException {
        chatClients.add(chatClient);
    }

    public void broadcastMessage(String name, String message) throws RemoteException {
        System.out.println(name + ": " + message);
        for(ChatClientInterface chatClient : chatClients) {
            chatClient.receiveMessage(name, message);
        }
    }

    public static void main(String[] args) throws Exception {
        ChatServerInterface chatServer = new ChatServerImpl();
         /*Para conversar com outra pessoa, apenes altere o "localhost" para o seu ip e assim você consegue entrar por outras máquina */
        Naming.rebind("rmi://localhost/ChatServer", chatServer);
        System.out.println("Server no ar...");
    }
}
