/**
 * Clase que almacena la informacion de las aristas en el grafo.
 * 
 * Luiscarlo Rivera, 09-11020 
 *	Jose Prado, 09-11006
 * 
 * Proyecto 5
 *	Prof Lab: Juan Arocha
 * 
 **/
public class Arco implements Comparable<Arco> {

	/**
	 * Nodo fuente del arco
	 */
	private int src = -1;
	/**
	 * Nodo destino del arco
	 */
	private int dst = -1;

	/**
	 * Peso del arco
	 */
	private int peso = Integer.MAX_VALUE;

	/**
	 * Crea una arista entre los vertices src y dst.
	 * 
	 * @param src id del Nodo fuente del arco
	 * @param dst id del Nodo destino del arco
	 **/
	public Arco(int src, int dst) {
		this.src = src;
		this.dst = dst;
	}

	/**
	 * @param src uno de los extremos del arco
	 * @param dst el otro extremo
	 * @param peso peso del arco
	 */
	public Arco(int src, int dst, int peso) {
		this.src = src;
		this.dst = dst;
		this.peso = peso;
	}

	/**
	 * Retorna una nueva arista que es copia de this.
	 **/
	@Override
	protected Object clone() {
		return new Arco(this.src, this.dst);
	}

	/**
	 * Indica si la arista de entrada es igual a this.
	 **/
	@Override
	public boolean equals(Object o) {
		Arco a;
		if (o == null) {
			return false;
		}

		if (!(o instanceof Arco)) {
			return false;
		}

		a = (Arco) o;

		if (this.src == a.getSrc() && this.dst == a.getDst()) {
			return true;
		}
		return false;
	}

	/**
	 * Retorna el vertice src de la arista.
	 * 
	 * @return id del Nodo fuente del Arco
	 **/
	public int getSrc() {
		return (this.src);
	}

	/**
	 * Retorna el vertice dst de la arista.
	 * 
	 * @return id del Nodo destino del Arco
	 **/
	public int getDst() {
		return (this.dst);
	}

	/**
	 * @return the peso
	 */
	public int getPeso() {
		return this.peso;
	}

	/**
	 * @param peso the peso to set
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Retorna la representacion en String de la arista.
	 **/
	@Override
	public String toString() {
		return "(" + this.src + ", " + this.dst + ") " + this.peso;
	}

	@Override
	public int compareTo(Arco a) {
		if (this.peso == a.getPeso()) {
			return 0;
		} else if (this.peso < a.getPeso()) {
			return -1;
		}
		return 1;
	}
} /* Fin de arco */
