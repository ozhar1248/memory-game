package memory.game;

public class MemoryGame {
    public static void main(String[] args) {
        PackageReceiver center = new ClientCenter(QueuesClient.out);
        new ThreadPullingFromQueue(center, QueuesClient.in).start();
        ConnectToServer c = new ConnectToServer("localhost", QueuesClient.in, QueuesClient.out);
    }
}
