/**
 * Representa un Conjunto disjunto de bosques
 * Implementacion del libro
 * Introduction to Algorithms Thomas H. Cormen
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 *
 * Proyecto 5
 * Prof Lab: Juan Arocha
 */
public class DisjointSet {

	/**
	 * Arreglo de "padres" o "representantes", el padre de la posicion
	 * del arreglo es el valor en esa posicion
	 */
	private int[] repres;
	/**
	 * Arreglo que dice la profundidad del arbol del representante en la
	 * pocision K del arreglo
	 */
	private int[] rango;
	/**
	 * Numero de conjuntos disjuntos 
	 */
	int conexas;
	/**
	 * @param numNodos 
	 * 
	 */
	public DisjointSet(int numNodos) {
		this.repres = new int [numNodos+1];
		this.rango = new int [numNodos+1];

		for(int i=0;i<numNodos+1;i++){
			this.repres[i] = i;
		}

		this.conexas = numNodos;
	}
	/**
	 * @return the repres
	 */
	public int[] getRepres() {
		return this.repres;
	}

	/**
	 * @return the rango
	 */
	public int[] getRango() {
		return this.rango;
	}
	
	/**
	 * @return el numero de conjuntos en el DisjointSet
	 */
	public int getConexas(){
		return this.conexas;
	}

	/**
	 * @param x
	 * @return el representante de x
	 */
	public int find(int x){
		if (x!=this.repres[x]){
			return find(this.repres[x]);
		}
		return x;
	}
	
	
	/**
	 * @param x
	 * @param y
	 * @return true si se unieron x, y , false si x, y ya estaban en el mismo
	 * conjunto
	 */
	public boolean union(int x, int y){
		return this.link(find(x),find(y));
	}
	
	/**
	 * @param px representante x
	 * @param py representante y
	 * @return true si cambio el representante de x o y, false si 
	 * tienen el mismo (ya estan unidos)
	 */
	private boolean link(int px, int py){
		if(px == py){
			return false;
		}
		this.conexas--; 
		if(this.rango[px]>this.rango[py]){
			this.repres[py] = px;
		}else if(this.rango[py]>this.rango[px]){
			this.repres[px] = py;
		}else{
			this.repres[py] = px;
			this.rango[px]++;	
		}
		return true;
	}

}
