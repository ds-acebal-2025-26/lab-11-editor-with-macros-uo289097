package es.uniovi.eii.ds.instruction.macros;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.ds.archivo.Archivo;
import es.uniovi.eii.ds.instruction.Instruction;

public class Macro implements Instruction {

    private List<Instruction> instructions = new ArrayList<>();
    private String name;

    public Macro(String name) {
        this.name = name;
    }

    @Override
    public void execute(Archivo archivo) {
        for (Instruction instruction : instructions) {
            instruction.execute(archivo);
        }
    }

    public String getName() {
        return name;
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

}
