package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.archivo.Archivo;

public class DeleteInstruction implements Instruction {

    public DeleteInstruction() {
    }

    @Override
    public void execute(Archivo archivo) {
        archivo.delete();
    }

}
