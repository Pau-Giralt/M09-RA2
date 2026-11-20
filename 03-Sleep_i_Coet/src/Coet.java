import java.util.Scanner;

public class Coet {

    public static final int NUM_MOTORS = 4;
    public static final int POTENCIA_MINIMA = 0;
    public static final int POTENCIA_MAXIMA = 10;

    private final Motor[] motors;

    public Coet() {
        motors = new Motor[NUM_MOTORS];
        for (int i = 0; i < NUM_MOTORS; i++) {
            motors[i] = new Motor(i);
        }
    }

    public void passaAPotencia(int p) {
        if (p < POTENCIA_MINIMA || p > POTENCIA_MAXIMA) {
            throw new IllegalArgumentException("Potència incorrecta: ha d'estar entre " + POTENCIA_MINIMA + " i " + POTENCIA_MAXIMA);
        }
        System.out.println("Passant a potència " + p);
        for (Motor motor : motors) {
            motor.setPotencia(p);
        }
    }

    public void arranca() {
        for (Motor motor : motors) {
            motor.start();
        }
        Scanner teclat = new Scanner(System.in);
        int potencia;
        do {
            potencia = teclat.nextInt();
            try {
                passaAPotencia(potencia);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (potencia != 0);
    }

    public static void main(String[] args) {
        Coet coet = new Coet();
        coet.arranca();
    }
}
