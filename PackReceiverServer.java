package memory.game;

public class PackReceiverServer implements PackageReceiver{
    private final ClientsGamesMap clientsGames;
    
    public PackReceiverServer(ClientsGamesMap clientsGames)
    {
        this.clientsGames = clientsGames;
    }
    
    @Override
    public void ReceivePackage(Package _package)
    {
        int op = _package.getOpertionNumber();
        int id = _package.getClientNumber();
        switch (op)
        {
            case 2: int cardNum = Integer.parseInt(_package.getMessage());
                        chooseCard(id, cardNum);
                        break;
        }
    }
    
    private void chooseCard(Integer id, int card)
    {
        clientsGames.getGame(id).chooseCard(card);
    }
    
}
