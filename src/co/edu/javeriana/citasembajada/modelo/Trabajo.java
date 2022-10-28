package co.edu.javeriana.citasembajada.modelo;

public class Trabajo extends Visa {

	// ----------------------------
	//  Atributos
	// ----------------------------
	private String empresa;
	private String cargo;

	// ----------------------------
	//  Relaciones
	// ---------------------------
	// ----------------------------
	//  Constructores
	// ----------------------------
	public Trabajo(String pId, double pValor, String pEmpresa, String pCargo) {
		super(pId, pValor);
		empresa = pEmpresa;
		cargo = pCargo;
	}

	// ----------------------------
	//  Metodos
	// ---------------------------
	public String getEmpresa() {
		return empresa;
	}

	public String getCargo() {
		return cargo;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public void agregarSolicitud(Solicitud solicitud) {
		this.setSolicitud(solicitud);
		
	}
}
