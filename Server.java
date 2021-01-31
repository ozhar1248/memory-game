package memory.game;

/*
    Main function of the server
*/
public class Server {
    public static void main(String[] args)
    {
        ClientsGamesMap clientsGames = new ClientsGamesMap();
        PackReceiverServer game_center = new PackReceiverServer(clientsGames);
        // thread that pull packages from the queue_in
        new ThreadPullingFromQueue(game_center, QueuesServer.in).start();
        ConnectToClient c = new ConnectToClient(clientsGames);
    }
}
