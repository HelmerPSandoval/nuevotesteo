package cl.ucn.disc.pa.tragamonedas;

/**
 * Blase Basica de Main
 *
 * @author Programacion Avanzada.
 */
public class Main {

    /**
     * The Main.
     *
     * @param args to use.
     */
    public static void main(String[] args) {

        // pantalla de entrada
        java.util.Scanner in = new java.util.Scanner(System.in);

        // saldo inicial
        int saldo = 1000;

        // valores iniciales
        int[] ruedas = new int[3];

        // imprimir bienvenida en antalla
        System.out.println("Bienvenido al Tragamonedas");
        System.out.println("============================================");

        // mientras tenga saldo para jugar
        while (saldo != 0) {

            System.out.print("Su saldo actual es de $" + saldo + ".  ¿Cuanto desea apostar? ");
            int apuesta = in.nextInt();
            System.out.println();

            // si la apuesta es positiva y menor que el saldo
            if (apuesta > 0 && apuesta <= saldo) {

                // descuento la apuesta
                saldo -= apuesta;

                // giro las 3 ruedas
                ruedas[0] = (int) (Math.random() * 10);
                ruedas[1] = (int) (Math.random() * 10);
                ruedas[2] = (int) (Math.random() * 10);

                // imprimo las puedas
                imprimirRuedas(ruedas);

                // calculo el premio
                int premio = obtenerPremio(apuesta, ruedas);

                if (premio > 0) {
                    saldo += premio;
                    System.out.println("Ud. obtiene $" + premio + "!");
                }

            } else {
                // si la apuesta es igual a 0 termina el programa y entrega el saldo agradeciendo al jugador
                if (apuesta == 0) {
                    System.out.println("Muchas gracias por Jugar.  Su saldo final es de $" + saldo + ".");
                    break;
                } else {
                    // si la apuesta es menor que 0 le pide al jugador que ingrese un monto correcto
                    System.out.println("Por favor, ingrese apuestas mayores que 0 para jugar o 0 para salir");
                }
            }
        }

        // si el saldo llega a 0 termina el programa y agradece al jugador
        if (saldo == 0) {
            System.out.println("Muchas Gracias por jugar.  Mejor suerte la próxima vez.");
        }

    }

    /**
     * Imprime los valores del arreglo de ruedas.
     *
     * @param ruedas arreglo donde estan los valores.
     */
    private static void imprimirRuedas(int[] ruedas) {

        // imprimo las ruedas
        System.out.println("+---+---+---+");
        // FIXME: cambiar para imprimir * en vez de un cero.
        System.out.print("| " + ruedas[0] + " ");
        System.out.print("| " + ruedas[1] + " ");
        System.out.print("| " + ruedas[2] + " ");
        System.out.println("|");
        System.out.println("+---+---+---+");
        System.out.println();
    }

    /**
     * Calcula el premio a recibir.
     *
     * @param apuesta valor apostado.
     * @param ruedas  arreglo de las ruedas.
     * @return el valor del premio.
     */
    private static int obtenerPremio(int apuesta, int[] ruedas) {

        // si los tres simbolos son iguales y distintos a * gana el valor del dígito multiplicado por la apuesta.
        if (ruedas[0] == ruedas[1] && ruedas[1] == ruedas[2] && ruedas[0] != 0) {
            return ruedas[0] * apuesta;
        }

        // cuenta las veces que aparece un cero
        int count = 0;
        for (int i = 0; i < ruedas.length; i++) {
            if (ruedas[i] == 0) {
                count = count + 1;
            }
        }

        // asigna el premio segun las veces que * es encontrado
        switch (count) {
            case 1:
                return 50;
            case 2:
                return 300;
            case 3:
                return 500;
            default:
                return 0;
        }
    }

}
