public class Fil extends Thread {
    private int delay;
    private boolean feinaSimulada;
    private String paraulaFinal;

    public Fil(String nom, int prioritat, int delay, boolean feinaSimulada, String paraulaFinal) {
        super(nom);
        setPriority(prioritat);
        this.delay = delay;
        this.feinaSimulada = feinaSimulada;
        this.paraulaFinal = paraulaFinal;
    }

    public Fil(String nom, int prioritat, int delay, boolean feinaSimulada) {
        this(nom, prioritat, delay, feinaSimulada, "Acaba");
    }

    @Override
    public void run() {
        for (int i = 1; i <= 9; i++) {
            if (feinaSimulada) {
                long comptador = 0;
                for (int j = 0; j < 1000; j++) {
                    comptador++;
                }
            }

            System.out.println(getName() + " " + i);

            if (delay > 0) {
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }
        System.out.println(paraulaFinal + " el fil " + getName());
    }
}
