package co.edu.javeriana.citasembajada.modelo;

public class Estudiante extends Visa{
	// ----------------------------
	//  Atributos
	// ----------------------------
	private String escolaridad;
	private String institucion;

	// ----------------------------
	//  Relaciones
	// ---------------------------
	// ----------------------------
	//  Constructores
	// ----------------------------
	public Estudiante(String pId, double pValor, String pEscolaridad, String pInstitucion) {
		super(pId, pValor);
		escolaridad = pEscolaridad;
		institucion = pInstitucion;
	}
	
	// ----------------------------
	//  Metodos
	// ---------------------------

	public String getEscolaridad() {
		return escolaridad;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setEscolaridad(String escolaridad) {
		this.escolaridad = escolaridad;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	@Override
	public void agregarSolicitud(Solicitud solicitud) {
		this.setSolicitud(solicitud);
	}


}
