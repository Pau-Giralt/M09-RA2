import java.util.Random;

public class DormAleatori extends Thread {

    public static final int NUM_ITERACIONS = 10;
    public static final int MAX_INTERVAL = 1000;

    private static final Random RANDOM = new Random();

    private final long instantCreacio;

    public DormAleatori(String nom) {
        super(nom);
        this.instantCreacio = System.currentTimeMillis();
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_ITERACIONS; i++) {
            int interval = RANDOM.nextInt(MAX_INTERVAL);
            long total = System.currentTimeMillis() - instantCreacio;
            System.out.printf("%-4s(%d) a dormir %5dms total %6dms%n", getName(), i, interval, total);
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");

        joan.start();
        pep.start();

        System.out.println("-- Fi de main -----------");
    }
}
