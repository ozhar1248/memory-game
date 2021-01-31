package memory.game;

public class ClientCenter implements PackageReceiver
{
    ClientWindow game;
    
    public ClientCenter()
    {
        game = null;
    }
    public void ReceivePackage(Package _package)
    {
        int card;
        int op = _package.getOpertionNumber();
        switch (op)
        {
            case 0: int size = Integer.parseInt(_package.getMessage());
                    game = new ClientWindow(size, QueuesClient.out);
                    break;
            case 1: int turn = Integer.parseInt(_package.getMessage());
                    game.setTurn((turn==1));
                    break;
            case 3: String[] tokens = _package.getMessage().split(" ");
                    card = Integer.parseInt(tokens[0]);
                    int indexColor = Integer.parseInt(tokens[1]);
                    game.showCard(card, indexColor);
                    break;
            case 4: card = Integer.parseInt(_package.getMessage());
                    game.showOffCard(card);
                    break;
            case 5: String message = _package.getMessage();
                    endGame(message);
        }
    }
    
    private void endGame(String outcome)
    {
        // to complete
    }
}
