package co.edu.javeriana.citasembajada.modelo;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Visa implements Serializable{
	//--------------------
	//	Constantes
	//--------------------

	//--------------------
	//	Atributos
	//-------------------
	protected String id;
	protected double valor;

	//--------------------
	//	Relaciones
	//-------------------
	/**
	 * Una visa tiene una lista de requisitos
	 * Una visa tiene un lista de solicitudes
	 */
	protected List<Requisito> requisitos;
	protected Solicitud solicitud;

	//--------------------
	//	Constructor
	//-------------------

	public Visa(String id, double valor) 
	{
		this.id = id;
		this.valor = valor;
		requisitos = new ArrayList<Requisito>();
	}
	//---------------------
	// Metodos
	//---------------------
	
	
		
	public double getValor() {
		return valor;
	}

	public String getId() {
		return id;
	}
	
	public List<Requisito> getRequisitos() {
		return requisitos;
	}
	
	public Solicitud getSolicitud() {
		return solicitud;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setRequisitos(List<Requisito> requisitos) {
		this.requisitos = requisitos;
	}
	
	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public abstract void agregarSolicitud(Solicitud solicitud);
	
	public String toString() {
		return (getClass().getSimpleName());
	}
}
