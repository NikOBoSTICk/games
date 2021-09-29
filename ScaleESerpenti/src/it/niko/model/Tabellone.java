package it.niko.model;

import java.util.HashMap;
import java.util.Map;

public class Tabellone {
    private final int fine;
    private final int righe;
    private final Map<Integer, Integer> caselleRighe;
    private final Map<Integer, CaselleSpeciali>  caselleOccupate;
    private final Map<Integer, Integer> serpenti;
    private final Map<Integer, Integer> scale;

    public Tabellone(int fine, int righe, int colonne) {
        if(righe * colonne != fine) throw new IllegalArgumentException();
        if(righe < 3 || colonne < 3) throw new IllegalArgumentException();
        this.fine = fine;
        this.righe = righe;

        this.caselleRighe = new HashMap<>();
        int x = 0;
        for(int i=righe-1; i>=0; i--)
            for(int j=0; j<colonne; j++)
                caselleRighe.put(x++, i);

        caselleOccupate = new HashMap<>();
        serpenti = new HashMap<>();
        scale = new HashMap<>();
    }

    public boolean aggiungiSerpente(int testa, int coda) {
        if(testa < coda || testa < righe || testa == fine)
            return false;
        if(caselleRighe.get(testa).equals(caselleRighe.get(coda)))
            return false;
        if(caselleOccupate.containsKey(testa) || caselleOccupate.containsKey(coda))
            return false;
        caselleOccupate.put(testa, CaselleSpeciali.testa);
        caselleOccupate.put(coda,  CaselleSpeciali.coda);
        serpenti.put(testa, coda);
        return true;
    }

    public boolean rimuoviSerpente(int testa, int coda) {
        if(!caselleOccupate.containsKey(testa) && !caselleOccupate.containsKey(coda))
            return false;
        caselleOccupate.remove(testa);
        caselleOccupate.remove(coda);
        serpenti.remove(testa);
        return true;
    }

    public boolean aggiungiScala(int base, int cima) {
        if(base > cima || cima < righe || base > fine-righe)
            return false;
        if(caselleRighe.get(base).equals(caselleRighe.get(cima)))
            return false;
        if(caselleOccupate.containsKey(base) || caselleOccupate.containsKey(cima))
            return false;
        caselleOccupate.put(base, CaselleSpeciali.base);
        caselleOccupate.put(cima, CaselleSpeciali.cima);
        scale.put(base, cima);
        return true;
    }

    public boolean rimuoviScala(int base, int cima) {
        if(!caselleOccupate.containsKey(base) && !caselleOccupate.containsKey(cima))
            return false;
        caselleOccupate.remove(base);
        caselleOccupate.remove(cima);
        scale.remove(base);
        return true;
    }

    public boolean aggiungiCasellaSpeciale(int posizione, CaselleSpeciali tipo) {
        if(caselleOccupate.containsKey(posizione))
            return false;
        caselleOccupate.put(posizione, tipo);
        return true;
    }

    public boolean rimuoviCasellaSpeciale(int posizione) {
        if(!caselleOccupate.containsKey(posizione))
            return false;
        caselleOccupate.remove(posizione);
        return true;
    }

    public CaselleSpeciali contenutoCasella(int posizione) {
        return caselleOccupate.get(posizione);
    }

    public int punta(int posizione){
        return switch(caselleOccupate.get(posizione)) {
            case testa -> serpenti.get(posizione);
            case base  -> scale.get(posizione);
            default -> posizione;
        };
    }
}
