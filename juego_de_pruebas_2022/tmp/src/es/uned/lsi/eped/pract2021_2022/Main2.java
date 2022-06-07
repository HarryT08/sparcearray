package es.uned.lsi.eped.pract2021_2022;

import java.io.IOException;

public class Main2 {

    public static void main(String[] args) {
        String[] arg = {"BTREE", "juego_de_pruebas_2022/pruebas/JuegoPruebas_Estudiantes_30000_100000.dat", "juego_de_pruebas_2022/salida/Salida_Estudiantes_30000_100000_BTREE.dat"};
        try {
            Main.main(arg);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
