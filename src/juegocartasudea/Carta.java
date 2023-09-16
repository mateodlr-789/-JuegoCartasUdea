/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegocartasudea;

import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import juegocartasudea.Constantes.NombreCarta;
import juegocartasudea.Constantes.Pinta;

/**
 *
 * @author Mateo
 */
public class Carta {

    private int indice;

    public Carta(Random r) {
        indice = r.nextInt(52) + 1;
    }

    public Pinta obtenerPinta() {
        if (indice <= 13) {
            return Pinta.TREBOL;
        } else if (indice <= 26) {
            return Pinta.PICA;
        } else if (indice <= 39) {
            return Pinta.CORAZON;
        } else {
            return Pinta.DIAMANTE;
        }
    }

    public NombreCarta obtenerNombre() {
        int numero = indice % 13;
        if (numero == 0) {
            numero = 13;
        }
        return NombreCarta.values()[numero - 1];
    }

    public void mostrarCarta(int x, int y, JPanel pnl) {
        String nombreImagen = "/Cartas/CARTA" + String.valueOf(indice) + ".JPG";
        try {
            ImageIcon imagen = new ImageIcon(getClass().getResource(nombreImagen));
            JLabel lblCarta = new JLabel(imagen);
            lblCarta.setBounds(x, y,
                    x + imagen.getIconWidth(),
                    y + imagen.getIconHeight());
            pnl.add(lblCarta);

        } catch (Exception e) {
            System.out.println("error al cargar la imagen " + e);
        }
    }

    public int getIndice() {
        return indice;
    }
}
