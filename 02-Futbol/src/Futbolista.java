import java.util.Random;

public class Futbolista extends Thread {

    public static final int NUM_JUGADORS = 11;
    public static final int NUM_TIRADES = 20;
    public static final float PROBABILITAT = 0.5f;

    private static final Random RANDOM = new Random();

    private int ngols;
    private int ntirades;

    public Futbolista(String nom) {
        super(nom);
        this.ngols = 0;
        this.ntirades = 0;
    }

    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            ntirades++;
            if (RANDOM.nextFloat() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    public int getNgols() {
        return ngols;
    }

    public int getNtirades() {
        return ntirades;
    }

    public static void main(String[] args) {
        String[] noms = {
            "Piqué", "Vinicius", "Torres", "Ramos", "Ronaldo", "Lewan",
            "Belli", "Arnau", "Aspas", "Messi", "MBapé"
        };

        // 1. Es creen tots els fils
        Futbolista[] jugadors = new Futbolista[NUM_JUGADORS];
        for (int i = 0; i < NUM_JUGADORS; i++) {
            jugadors[i] = new Futbolista(noms[i]);
        }

        // 2. S'inicien tots els fils
        System.out.println("Inici dels xuts --------------------");
        for (Futbolista f : jugadors) {
            f.start();
        }

        // 3. S'espera a tots els fils a que acabin
        for (Futbolista f : jugadors) {
            try {
                f.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
        System.out.println("Fi dels xuts -----------------------");

        // 4. Es mostren les estadístiques dels xuts
        System.out.println("--- Estadístiques ------");
        for (Futbolista f : jugadors) {
            System.out.printf("%-10s-> %2d gols%n", f.getName(), f.getNgols());
        }
    }
}
