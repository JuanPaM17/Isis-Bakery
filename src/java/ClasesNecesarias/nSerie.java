/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClasesNecesarias;

/**
 *
 * @author Santiago Torres
 */
public class nSerie {

    public int generarParte() {
        int numero = (int) (Math.random() * 10000 + 1);
        return numero;
    }

    public String numeroSerie() {
        String re;
        re = String.valueOf(generarParte()) + String.valueOf(generarParte());
        return re;
    }
}
