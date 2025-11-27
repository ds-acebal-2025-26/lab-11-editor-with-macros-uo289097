package es.uniovi.eii.ds.main;

import java.io.*;
import java.util.Arrays;

import es.uniovi.eii.ds.archivo.Archivo;
import es.uniovi.eii.ds.instruction.DeleteInstruction;
import es.uniovi.eii.ds.instruction.InsertInstruction;
import es.uniovi.eii.ds.instruction.OpenInstruction;
import es.uniovi.eii.ds.instruction.ReplaceInstruction;
import es.uniovi.eii.ds.instruction.macros.MacroRecorder;

public class Main {

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	// Represents the document of the editor.
	Archivo archivo = new Archivo();
	private MacroRecorder macroRecorder = new MacroRecorder(archivo);

	public static void main(String[] args) {
		new Main().run();
	}

	// Main program loop.
	public void run() {
		drawLogo();
		showHelp();

		while (true) {
			UserCommand command = promptUser();
			String[] args = command.args;

			switch (command.name) {
				case "open" -> {
					macroRecorder.addInst(new OpenInstruction(args[0]));
				}
				case "insert" -> {
					macroRecorder.addInst(new InsertInstruction(args));
				}
				case "delete" -> {
					macroRecorder.addInst(new DeleteInstruction());
				}
				case "replace" -> {
					macroRecorder.addInst(new ReplaceInstruction(args[0], args[1]));
				}
				case "help" -> showHelp();
				case "record" -> {
					macroRecorder.record(args[0]);
				}
				case "stop" -> {
					macroRecorder.stop();
				}
				case "execute" -> {
					macroRecorder.play(args[0]);
				}
				default -> {
					System.out.println("Unknown command");
					continue;
				}
			}

			System.out.println(archivo.getText());
		}
	}

	// $-- Some individual user commands that do a bit more work ---------------

	/*
	 * private void open(String[] args) {
	 * if (!checkArguments(args, 1, "open <file>"))
	 * return;
	 * try {
	 * String filename = args[0];
	 * text = new StringBuilder(readFile(filename));
	 * } catch (Exception e) {
	 * System.out.println("Document could not be opened");
	 * }
	 * }
	 * 
	 * private String readFile(String filename) {
	 * InputStream in = getClass().getResourceAsStream("/" + filename);
	 * if (in == null)
	 * throw new IllegalArgumentException("File not found: " + filename);
	 * 
	 * try (BufferedReader input = new BufferedReader(new InputStreamReader(in))) {
	 * StringBuilder result = new StringBuilder();
	 * String line;
	 * boolean firstLine = true;
	 * while ((line = input.readLine()) != null) {
	 * if (!firstLine)
	 * result.append(System.lineSeparator());
	 * result.append(line);
	 * firstLine = false;
	 * }
	 * return result.toString();
	 * } catch (IOException e) {
	 * throw new UncheckedIOException(e);
	 * }
	 * }
	 * 
	 * private void replace(String[] args) {
	 * if (!checkArguments(args, 2, "replace <find> <replace>"))
	 * return;
	 * String find = args[0];
	 * String replace = args[1];
	 * text = new StringBuilder(text.toString().replace(find, replace));
	 * }
	 */

	// $-- Auxiliary methods ---------------------------------------------------

	// YOU DON'T NEED TO UNDERSTAND OR MODIFY THE CODE BELOW THIS LINE

	private record UserCommand(String name, String[] args) {
	}

	// Prompts the user and reads a line of input and returns it as a record with
	// the command and its arguments. If EOF is reached (i.e., there are nothing to
	// read), an error occurs or the user types "exit", the program exits. If there
	// are no arguments, the args array is empty.
	//
	// Example:
	//
	// > insert "no quiero acordarme" --> returns UserInput("insert", ["no",
	// "quiero", "acordarme"])
	// > delete --> returns UserInput("delete", [])
	//
	private UserCommand promptUser() {
		while (true) {
			System.out.print("> ");
			try {
				String line = in.readLine();
				if (line == null)
					System.exit(0);
				if (line.equals("exit"))
					exit();
				if (line.isBlank())
					continue;
				String[] parts = line.split("\\s+");
				return new UserCommand(parts[0], Arrays.copyOfRange(parts, 1, parts.length));
			} catch (IOException e) {
				System.out.println("Error reading input");
				System.exit(2);
			}
		}
	}

	private boolean checkArguments(String[] args, int expected, String syntax) {
		if (args.length != expected) {
			System.out.println("Invalid number of arguments => " + syntax);
			return false;
		}
		return true;
	}

	private void exit() {
		System.out.println("Goodbye!");
		System.exit(0);
	}

	private void drawLogo() {
		System.out.println(LOGO);
	}

	private void showHelp() {
		System.out.println(HELP);
	}

	private static final String LOGO = """

			███╗   ███╗ █████╗  ██████╗████████╗███████╗██╗  ██╗
			████╗ ████║██╔══██╗██╔════╝╚══██╔══╝██╔════╝╚██╗██╔╝
			██╔████╔██║███████║██║        ██║   █████╗   ╚███╔╝
			██║╚██╔╝██║██╔══██║██║        ██║   ██╔══╝   ██╔██╗
			██║ ╚═╝ ██║██║  ██║╚██████╗   ██║   ███████╗██╔╝ ██╗
			╚═╝     ╚═╝╚═╝  ╚═╝ ╚═════╝   ╚═╝   ╚══════╝╚═╝  ╚═╝
			""";

	private static final String HELP = """
			┌──────────────────────┬─────────────────────────────────────────────┐
			│ open <file>          │                                             │
			│ insert <text>        │ append text to the end                      │
			│ delete               │ delete the last word                        │
			│ replace <a> <b>      │ replace <a> with <b> in the whole document  │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ record <macro>       │ start recording a macro                     │
			│ stop                 │ stop recording                              │
			│ execute <macro>      │ execute the specified macro                 │
			├──────────────────────┼─────────────────────────────────────────────┤
			│ help                 │                                             │
			│ exit                 │                                             │
			└──────────────────────┴─────────────────────────────────────────────┘
			""";
}
