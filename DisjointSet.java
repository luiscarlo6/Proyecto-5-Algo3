/**
 * Representa un Conjunto disjunto de bosques
 * Implementacion del libro
 * Introduction to Algorithms Thomas H. Cormen
 */

/**
 * @author Luiscarlo
 *
 */
public class DisjointSet {

	private int[] repres;
	private int[] rango;
	int conexas;
	/**
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
		return repres;
	}

	/**
	 * @return the rango
	 */
	public int[] getRango() {
		return rango;
	}
	
	public int getConexas(){
		return this.conexas;
	}

	public int find(int x){
		if (x!=this.repres[x]){
			return find(this.repres[x]);
		}
		else{
			return x;
			
		}
	}
	
	
	public boolean union(int x, int y){
		return this.link(find(x),find(y));
	}
	
	private boolean link(int px, int py){
		if(px == py){
			return false;
		}else{		
			this.conexas--; 
			if(rango[px]>rango[py]){
				repres[py] = px;
			}else if(rango[py]>rango[px]){
				repres[px] = py;
			}else{
				repres[py] = px;
				rango[px]++;	
			}
			return true;
		}
	}

}
