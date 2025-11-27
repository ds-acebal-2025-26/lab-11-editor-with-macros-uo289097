package es.uniovi.eii.ds.instruction.macros;

import java.util.ArrayList;
import java.util.List;

import es.uniovi.eii.ds.archivo.Archivo;
import es.uniovi.eii.ds.instruction.Instruction;

public class MacroRecorder {

    private Macro currentMacro;
    private boolean recording = false;
    private List<Macro> macros = new ArrayList<>();
    private Archivo archivo;

    public MacroRecorder(Archivo archivo) {
        this.archivo = archivo;
    }

    public void addInst(Instruction instruction) {
        instruction.execute(archivo);
        if (recording && currentMacro != null) {
            currentMacro.addInstruction(instruction);
        }
    }

    public void stop() {
        recording = false;
        macros.add(currentMacro);
        currentMacro = null;

    }

    public void record(String name) {
        recording = true;
        currentMacro = new Macro(name);
    }

    public void play(String name) {
        for (Macro macro : macros) {
            if (macro.getName().equals(name)) {
                macro.execute(archivo);
                return;
            }
        }
    }

}
