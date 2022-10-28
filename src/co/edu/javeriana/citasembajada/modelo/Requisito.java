package co.edu.javeriana.citasembajada.modelo;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.Serializable;

public class Requisito implements Serializable{
	//--------------------
	//	Constantes
	//--------------------

	//--------------------
	//	Atributos
	//--------------------
	private String descripcion;
	
	
	
	//--------------------
	//	Constructor
	//--------------------
	public Requisito(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	//--------------------
	//	Metodos
	//--------------------
	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
