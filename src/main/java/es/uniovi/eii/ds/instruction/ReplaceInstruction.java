package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.archivo.Archivo;

public class ReplaceInstruction implements Instruction {

    private String oldWord;
    private String newWord;

    public ReplaceInstruction(String oldWord, String newWord) {
        this.oldWord = oldWord;
        this.newWord = newWord;
    }

    @Override
    public void execute(Archivo archivo) {
        archivo.replace(oldWord, newWord);
    }

}
