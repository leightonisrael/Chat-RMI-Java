import java.io.Serial;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient {
    public static void main(String[] args) throws Exception {
        
        /*Para conversar com outra pessoa, apenes altere o "localhost" para o seu ip e assim você consegue entrar por outras máquina */   
        ChatServerInterface chatServer = (ChatServerInterface)Naming.lookup("rmi://localhost/ChatServer");
        ChatClientInterface chatClient = new ChatClientImpl(chatServer);
        chatServer.registerChatClient(chatClient);

        String name = System.console().readLine("Defina seu nome: ");
        chatClient.registerName(name);

        String message = "";
        while (!message.equals("exit")) {
            message = System.console().readLine();
            chatServer.broadcastMessage(name, message);
        }

        System.exit(0);
    }
}

class ChatClientImpl extends UnicastRemoteObject implements ChatClientInterface {
    @Serial
    private static final long serialVersionUID = 1L;

    protected ChatServerInterface chatServer;
    protected String name;

    public ChatClientImpl(ChatServerInterface chatServer) throws RemoteException {
        this.chatServer = chatServer;
    }

    public void receiveMessage(String name, String message) throws RemoteException {
        System.out.println(name + ": " + message);
    }

    public void registerName(String name) throws RemoteException {
        this.name = name;
        System.out.println("Registrado como " + name);
    }
}
