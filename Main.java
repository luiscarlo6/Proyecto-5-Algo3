import java.io.*;
import java.util.*;

public class Main {

	static Scanner s = null;
	static PrintWriter pw = null;
	static int costeEvento = -1;
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
			return;
		}

		try {//Abrir o crear archivo de salida
			archivoOut = new FileWriter(args[1]);
			pw = new PrintWriter(archivoOut);
		} catch (IOException e){
			System.out.println("El archivo no pudo ser creado o no existe");
			return;
		}			
		
		int numCasos = s.nextInt();
		
		for(int i = 1; i!=numCasos+1;i++){
			int numCity = s.nextInt();
			BinaryHeap<Arco> arcos = llenar();		
			
			int arr[] = kruskal(arcos,numCity);
			
			pw.println("Caso "+i+": "+arr[0]+ " "+arr[1]);
		}
		
		
		try {
			archivoOut.close();
		} catch (IOException e) {
		}
		s.close();
		pw.close();
		
	}
	
	private static BinaryHeap<Arco> llenar(){
		BinaryHeap<Arco> heap = new BinaryHeap<Arco>();		
		int numArcos = s.nextInt();
		costeEvento = s.nextInt();
		
		for(int i = 0; i!=numArcos;i++){
			Arco a = new Arco(s.nextInt(),s.nextInt(),s.nextInt());
//			System.out.println(a);
			heap.add(a);
		}
		
		return heap;
	}
	
	private static int[] kruskal(BinaryHeap<Arco> arcos, int numCity){
		DisjointSet E = new DisjointSet(numCity);
		int arr[] = new int[2];
		int costo = 0;
		int eventos = numCity;
		
		
		while (!arcos.esVacio()){
			Arco e = (Arco) arcos.min();
			arcos.remove();
			
			if (E.find(e.getSrc()) != E.find(e.getDst())) {
				E.union(e.getSrc(), e.getDst());
				if (costo + eventos*costeEvento>
					E.getConexas()*costeEvento+(costo+e.getPeso())){
					costo += e.getPeso();
					eventos = E.getConexas();
				}
			}
		}
		arr[0] = costo + eventos * costeEvento;
		arr[1] = eventos;
		return arr;
	}
}
