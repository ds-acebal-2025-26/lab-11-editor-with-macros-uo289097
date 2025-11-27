package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.archivo.Archivo;

public class InsertInstruction implements Instruction {

    private String[] words;

    public InsertInstruction(String[] words) {
        this.words = words;
    }

    @Override
    public void execute(Archivo archivo) {
        archivo.insert(words);
    }

}
