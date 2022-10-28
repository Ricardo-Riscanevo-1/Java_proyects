package co.edu.javeriana.citasembajada.modelo;

public class Turismo extends Visa {

	// ----------------------------
	//  Atributos
	// ----------------------------
	private int diasEstadia;

	// ----------------------------
	//  Relaciones
	// ---------------------------
	// ----------------------------
	//  Constructores
	// ----------------------------
	public Turismo(String pId, double pValor, int pDiasEstadia) {
		super(pId, pValor);
		diasEstadia = pDiasEstadia;
	}
	
	// ----------------------------
	//  Metodos
	// ---------------------------

	public int getDiasEstadia() {
		return diasEstadia;
	}

	public void setDiasEstadia(int diasEstadia) {
		this.diasEstadia = diasEstadia;
	}

	@Override
	public void agregarSolicitud(Solicitud solicitud) {
		this.setSolicitud(solicitud);
		
	}

}
