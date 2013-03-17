import java.io.*;
import java.util.*;

/**
 * @author luiscarlo
 *
 */
public class Main {
	/**
	 * Scanner para leer archivo de entrada
	 */
	static Scanner s = null;
	/**
	 * PrintWriter para escribir en archivo de salida
	 */
	static PrintWriter pw = null;
	/**
	 *Coste de los eventos en el caso que se este resolviendo
	 */
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

		if (!s.hasNextInt()){
			System.out.println("El archivo no cumple con el formato");
			return;
		}
		int numCasos = s.nextInt();


		for(int i = 1; i!=numCasos+1;i++){
			if (!s.hasNextInt()){
				System.out.println("El archivo no cumple con el formato");
				return;
			}
			int numCity = s.nextInt();
			BinaryHeap<Arco> arcos = llenar();		

			int arr[] = kruskal(arcos,numCity);

			pw.println("Caso "+i+": "+arr[0]+ " "+arr[1]);
		}


		try {
			archivoOut.close();
		} catch (IOException e){
			System.out.println("Error");
		}
		s.close();
		pw.close();

	}

	/**
	 * @return un Heap binario con todos los caminos entre las
	 * locaciones, el primero del heap es el camino de menor costo
	 */
	private static BinaryHeap<Arco> llenar(){
		BinaryHeap<Arco> heap = new BinaryHeap<Arco>();		
		if (!s.hasNextInt()){
			System.out.println("El archivo no cumple con el formato");
			System.exit(1);
		}
		int numArcos = s.nextInt();
		if (!s.hasNextInt()){
			System.out.println("El archivo no cumple con el formato");
			System.exit(1);
		}
		costeEvento = s.nextInt();

		for(int i = 0; i!=numArcos;i++){
			if (!s.hasNextInt()){
				System.out.println("El archivo no cumple con el formato");
				System.exit(1);
			}
			int dst = s.nextInt();
			if (!s.hasNextInt()){
				System.out.println("El archivo no cumple con el formato");
				System.exit(1);
			}
			int src = s.nextInt();
			if (!s.hasNextInt()){
				System.out.println("El archivo no cumple con el formato");
				System.exit(1);
			}
			int peso = s.nextInt();
			Arco a = new Arco(src,dst,peso);
			heap.add(a);
		}

		return heap;
	}

	/**
	 * Algoritmo de Kruskal modificado, devuelve elmenor costo dada
	 * una relacion de componentes conexas y costo de arcos
	 * @param arcos Heap de caminos (arcos)
	 * @param numCity numero de ciudades(nodos)
	 * @return arreglo donde la pos 0 es el costo minimo, y la pos 1 es
	 * el numero de componentes conexas con la que se genera ese costo
	 */
	private static int[] kruskal(BinaryHeap<Arco> arcos, int numCity){
		DisjointSet E = new DisjointSet(numCity);
		int arr[] = new int[2];
		int costo = 0;
		int eventos = numCity;


		while (!arcos.esVacio()&&E.getConexas()>1){
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
