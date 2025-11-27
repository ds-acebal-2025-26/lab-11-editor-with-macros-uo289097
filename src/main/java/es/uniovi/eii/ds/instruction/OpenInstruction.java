package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.archivo.Archivo;

public class OpenInstruction implements Instruction {

    private String filename;

    public OpenInstruction(String filename) {
        this.filename = filename;
    }

    @Override
    public void execute(Archivo archivo) {
        archivo.open(filename);
    }

}
