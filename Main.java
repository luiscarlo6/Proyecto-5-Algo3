import java.io.*;
import java.util.*;

public class Main {

	static Scanner s = null;
	static PrintWriter pw = null;
	/**
	 * @param args
	 *            Comandos de entrada
	 */
	public static void main(String args[]) {

		File archivoIn;
		FileWriter archivoOut;
		if (args.length != 2) {
			System.out.print("Error en la linea de argumentos");
			System.out.print("Debe ser Java Main archivo.in archivo.out");
			System.exit(0);
		}
		
		try {//Abriendo archivo de entrada
			archivoIn = new File(args[0]);
			s = new Scanner(archivoIn);
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser abierto o no existe");
		}

		try {//Abrir o crear archivo de salida
			archivoOut = new FileWriter(args[1]);
			pw = new PrintWriter(archivoOut);
		} catch (IOException e){
			System.out.println("El archivo no pudo ser creado o no existe");
		}			
		
		

		
	}
}
