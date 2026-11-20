import java.util.Random;

public class Motor extends Thread {

    public static final int TEMPS_MINIM = 1000;
    public static final int TEMPS_MAXIM = 2000;

    private static final Random RANDOM = new Random();

    private final int id;
    private int potenciaActual;
    private int potenciaObjectiu;
    private boolean ordreRebuda;

    public Motor(int id) {
        super("Motor " + id);
        this.id = id;
        this.potenciaActual = 0;
        this.potenciaObjectiu = 0;
        this.ordreRebuda = false;
    }

    public synchronized void setPotencia(int p) {
        potenciaObjectiu = p;
        ordreRebuda = true;
        notifyAll();
    }

    private synchronized boolean esperaFeina() {
        while (potenciaActual == potenciaObjectiu) {
            if (ordreRebuda && potenciaObjectiu == 0) {
                return false;
            }
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        return true;
    }

    private synchronized boolean fesPas() {
        String tipus;
        if (potenciaActual < potenciaObjectiu) {
            potenciaActual++;
            tipus = "Incre.";
        } else if (potenciaActual > potenciaObjectiu) {
            potenciaActual--;
            tipus = "Decre.";
        } else {
            return true;
        }
        if (potenciaActual == potenciaObjectiu) {
            tipus = "FerRes";
        }
        System.out.println("Motor " + id + ": " + tipus + " Objectiu: " + potenciaObjectiu + " Actual: " + potenciaActual);
        return potenciaActual == 0 && potenciaObjectiu == 0;
    }

    @Override
    public void run() {
        while (esperaFeina()) {
            int temps = TEMPS_MINIM + RANDOM.nextInt(TEMPS_MAXIM - TEMPS_MINIM + 1);
            try {
                Thread.sleep(temps);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            if (fesPas()) {
                return;
            }
        }
    }
}
