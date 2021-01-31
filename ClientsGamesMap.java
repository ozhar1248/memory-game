package memory.game;

import java.util.HashMap;

public class ClientsGamesMap {
    private final IndexesBook clientNums; //to give every client an index
    private int playerWaiting; // id of client that waiting. -1 if no one waiting
    private final HashMap<Integer, Game> clientGameMap; // every client index associates with a game
    
    public ClientsGamesMap()
    {
        clientNums = new IndexesBook();
        clientGameMap = new HashMap<>();
        playerWaiting = -1;
    }
    
    // Add a new client to the system
    public int addClient()
    {
        int new_id = clientNums.addIndex();
        if (playerWaiting < 0)
        {
            playerWaiting = new_id;
            //deliver message "waiting"
        }
        else
        {
            Game new_game = new Game(Colors.N, QueuesServer.out, new_id, playerWaiting);
            clientGameMap.put(new_id, new_game);
            clientGameMap.put(playerWaiting, new_game);
            playerWaiting = -1;
        }
        return new_id;
    }
    
    /* Removes id and also his couple*/
    public void RemoveClient(int id)
    {
        Game game = clientGameMap.get(id);
        if (game == null)
        {
            return;
        }
        //int otherPlayer = game.getOtherPlayer(id);
        
        clientGameMap.remove(id);
//        if (clientGameMap.get(otherPlayer) != null)
//        {
//            // send message to him
//            clientGameMap.remove(otherPlayer);
//        }
    }
    
    public Game getGame(int id)
    {
        return clientGameMap.get(id);
    }
}
