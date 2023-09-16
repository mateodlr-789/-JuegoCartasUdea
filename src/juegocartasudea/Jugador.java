/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartasudea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import javax.swing.JPanel;
import juegocartasudea.Constantes.Figura;
import juegocartasudea.Constantes.NombreCarta;
import juegocartasudea.Constantes.Pinta;

/**
 *
 * @author Mateo
 */
public class Jugador {

    private Random r;
    private Carta[] cartas;

    private Figura figuras;
    private NombreCarta[] cartasFigura;

    public Jugador() {
        r = new Random();
    }

    public void repartir() {
        cartas = new Carta[10];
        for (int i = 0; i < 10; i++) {
            cartas[i] = new Carta(r);
        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        for (int i = 0; i < 10; i++) {
            cartas[i].mostrarCarta(5 + i * 50, 5, pnl);
        }
        pnl.repaint();
    }

    public static boolean esEscalera(Carta[] cartas) {
        if (cartas == null || cartas.length < 3) {
            return false;
        }
        Arrays.sort(cartas, Comparator.comparingInt(Carta::getIndice));
        int contador = 1;
        for (int i = 1; i < cartas.length; i++) {
            if (cartas[i].getIndice() == cartas[i - 1].getIndice() + 1) {
                contador++;
            } else if (cartas[i].getIndice() != cartas[i - 1].getIndice()) {
                contador = 1;
            }
            if (contador > 3) {
                return true;
            }
        }
        return false;
    }

    public String getSumaTotal(ArrayList<NombreCarta> restCart, int resta) {
        int[] valoresCartas = {
            10, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10
        };
        int sumaTotal = 0;
        for (Carta carta : cartas) {
            int valorCarta = valoresCartas[carta.obtenerNombre().ordinal()];
            sumaTotal += valorCarta;
        }
        return "El puntaje es: " + (sumaTotal - resta);
    }

    public String obtenerFiguras() {
        String mensaje = "No hay grupos";
        ArrayList<NombreCarta> restCart = new ArrayList<NombreCarta>();
        int totalGrupos = 0;
        int[] contadores = new int[NombreCarta.values().length];
        int[] valoresCartas = {
            10, 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10
        };
        int[] numeroCartas = {
            0, 0, 2, 3, 4, 5, 6, 7, 8, 9, 10
        };
        int restTotal = 0;

        for (int i = 0; i < cartas.length; i++) {
            contadores[cartas[i].obtenerNombre().ordinal()]++;
        }

        for (int i = 0; i < contadores.length; i++) {
            if (contadores[i] >= 2) {
                totalGrupos++;
            }
        }
        if (totalGrupos > 0) {
            mensaje = "Los grupos encontrados fueron:\n";
            for (int i = 0; i < contadores.length; i++) {
                if (contadores[i] >= 2) {
                    mensaje += figuras.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                    restCart.add(NombreCarta.values()[i]);
                    int valorCarta = valoresCartas[NombreCarta.values()[i].ordinal()];
                    int mult = numeroCartas[figuras.values()[contadores[i]].ordinal()];
                    restTotal += valorCarta * mult;
                }
            }
        }
        boolean escalera = esEscalera(cartas);
        String suma = getSumaTotal(restCart, restTotal);
        if (escalera) {
            return mensaje + "Hay escalera\n" + suma;

        }
        return mensaje + suma;
    }
}
