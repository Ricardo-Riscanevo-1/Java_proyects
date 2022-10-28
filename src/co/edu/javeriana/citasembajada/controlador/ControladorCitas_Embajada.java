package co.edu.javeriana.citasembajada.controlador;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import co.edu.javeriana.citasembajada.modelo.SistemaCitas_Embajada;
import co.edu.javeriana.citasembajada.persistencia.Manejo_Archivos;
import co.edu.javeriana.citasembajada.vista.Interfaz_Consola;


public class ControladorCitas_Embajada {

	// ----------------------------
	//  Constantes
	// ----------------------------

	// ----------------------------
	//  Atributos
	// ----------------------------

	// ----------------------------
	//  Relaciones
	// ----------------------------

	/**
	 * 	embajadas
	 * 	vista
	 * 	persistencia
	 */
	private SistemaCitas_Embajada embajada;
	private Interfaz_Consola vista;
	//private Manejo_Archivos persistencia;

	// ----------------------------
	//  Constructores
	// ----------------------------
	/**
	 * 	
	 * El Constructor inicializa los atributos embajada, vista y persistencia   
	 *
	 */
	public ControladorCitas_Embajada()  
	{
		embajada = new SistemaCitas_Embajada();
		vista = new Interfaz_Consola();
		try
		{
			embajada = Manejo_Archivos.leerSerializado();
		}
		catch(Exception e)
		{
			vista.mostrarError(e.getMessage());
		}
		finally
		{
			try
			{	
				int opcionEscogidaMenu = vista.mostrarMenu();
				while(opcionEscogidaMenu != 9)
				{
					seleccionarOpcionMenuPrincipal(opcionEscogidaMenu);
					opcionEscogidaMenu = vista.mostrarMenu();
				}
				
				
				vista.imprimir("GRACIAS");
				Manejo_Archivos.escribirSerializado(embajada);
			}
			catch(IOException e)
			{
				vista.mostrarError(e.getMessage());
			}
			catch(NumberFormatException e)
			{
				vista.mostrarError("Dato invalido ");
			}
		}

	}
	/**
	 * El controlador  
	 */
	public static void main(String[] args)  
	{
		ControladorCitas_Embajada c = new ControladorCitas_Embajada();

	}

	// ----------------------------
	//  Metodos
	// ----------------------------
	/**
	 * Ejecuta la función seleccionada por el usuario a través del número de la opcion digitado en pantalla
	 * @param int opcionMenu 
	 * @throws Exception 
	 */
	public void seleccionarOpcionMenuPrincipal(int opcionMenu) 
	{
		if(opcionMenu == 0)
		{
			asociarPaisEmbajada();
		}
		if(opcionMenu == 1)
		{
			ingresarSolicitantes();
		}
		if(opcionMenu == 2)
		{
			leerVisa();
		}
		if(opcionMenu ==3)
		{
			hacerSolicitudTurismo();
		}
		if(opcionMenu ==4)
		{
			hacerSolicitudNoTurismo();	
		}
		if(opcionMenu ==5)
		{
			calcularValorVisa(); 
		}      
		if(opcionMenu ==6)
		{
			generarReporteCitasFecha();
		}
		if(opcionMenu ==7)
		{
			consultarTipoVisa();
		
		}
		if(opcionMenu == 8)
		{
			consultarBenefeciarios();
		}
		if(opcionMenu == 10)
		{
			imprimirEmbajada();			
			embajada.imprimirSolicitud();
			embajada.imprimirUsuario();
		}
		/*if(opcionMenu == 11)
		{
			embajada = Manejo_Archivos.leerSerializado();
		}
		if(opcionMenu == 12)
		{
			Manejo_Archivos.escribirSerializado(embajada);
		}*/

	}
	/**
	 * Asocia al sistema una embajada específica con sus respectivos atributos a traves del nombre de  un archivo con 
	 * la lista de embajadas pedido por pantalla al usuario
	 * @return void
	 */
	public void asociarPaisEmbajada() 
	{
		String[] datosEmbajada;
		try
		{
			String nombreArchivo = vista.leerString(" Escriba el nombre del archivo que contiene las embajadas: ");
			String nombrePais = vista.leerString(" Escriba el nombre del país de la embajada a asociar: (Presione enter antes de ingresar el dato)");
			datosEmbajada = Manejo_Archivos.leerEmbajada(nombreArchivo, embajada, nombrePais);
			
			embajada.setId(datosEmbajada[0]);
			embajada.setPaisEmbajada(datosEmbajada[1]);
			embajada.setMoneda(datosEmbajada[2]);
			embajada.setImpuesto(Float.valueOf(datosEmbajada[3]));
			embajada.setCambioOficial(Double.valueOf(datosEmbajada[4]));
			vista.imprimir("-------EMBAJADA ASOCIADA EXITOSAMENTE---------");
		}
		catch(Exception e)
		{

			vista.mostrarError(e.getMessage());
		}

	}
	/**
	 * Agrega consecutivamente la información de varios usuarios obtenidos a traves del nombre un archivo con los datos de los usuarios, pedido por pantalla al usuario
	 * @return void
	 */
	public void ingresarSolicitantes()
	{
		String nombreArchivo = vista.leerString(" Digite el nombre del archivo con los solicitantes: ");
		List<String[]> datosUsuarios;
		try
		{
			datosUsuarios = Manejo_Archivos.leerSolicitantes(nombreArchivo,embajada);

			for (String[] datos : datosUsuarios) 
			{			
				String numeroPasaporte = datos[0];
				String nombre = datos[1];
				String paisOrigen = datos[2];
				String ciudadNacimiento = datos[3];
				LocalDate fechaNacimiento = LocalDate.parse(datos[4], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String email = datos[5];
				if(datos.length == 6)
				{
					embajada.agregarUsuario(numeroPasaporte, nombre, paisOrigen, ciudadNacimiento, fechaNacimiento, email,null);
				}
				else
				{
					String adInformacion = datos[6];
					embajada.agregarUsuario(numeroPasaporte, nombre, paisOrigen, ciudadNacimiento, fechaNacimiento, email,adInformacion);
				}
				
				
				
			}vista.mostrarRecuadro("USUARIOS AGREGADOS EXITOSAMENTE");
		}
		catch(IOException e)
		{
			vista.mostrarError(e.getMessage());
		}
	}
	/**
	 * Ingresa al sistema uno o varios tipos de visas con sus respectivo requisitos, todo obtenido a traves de un archivo
	 * @return void
	 */
	public void leerVisa()
	{
		vista.mostrarRecuadro("OPCION TEMOPRALMENTE FUERA DE SERVICIO");
		/*String nombreArchivo = vista.leerString(" Digite el nombre del archivo con los requisitos de las visa:  ");
		try
		{
			List<List<String>> visas = new ArrayList<List<String>>();
			visas = Manejo_Archivos.leerVisas(embajada, nombreArchivo);
			for( List<String> visa : visas)
			{
			if(visaBuscada.equalsignorecase(visa.get(1))
			{
				tipoVisa = visa.get(1);
				visa.remove(1);
				for(String  requisito : requisitos)
				{
				vista.imprimir(requisito);
				}
			}	
			vista.imprimir("-------VISAS ASOCIADAS EXITOSAMENTE---------");
		}
		catch(IOException e)
		{
			vista.mostrarError(e.getMessage());
		}*/
	}
	/**
	 * Genera una solicitud de tipo turismo valida para un usuario, y en caso de que se desee anexar varios usuarios a la misma solicitud, 
	 * se accede a un archivo con el numero de pasaporte de cada uno de los usuarios a agregar a la solicitud Se le pide al usuario por pantalla la información 
	 * requerida para la visa de igual manera que el nombre del archivo donde se encuentra la tarifa de cada visa 
	 * @return void
	 * @throws Exception 
	 */
	//Turismo informacionDeVisa[0] = dias estadia
	//datosTipoDeVisa [0]=id  [1]=tipo de visa   [2]=tarifa
	public void hacerSolicitudTurismo()  
	{	
		List<String> numerosDePasaporte = new ArrayList<String>();
		String[] informacionDeVisa = new String[1];
		
		String numeroPasaporte = vista.leerString(" Digite su numero de Pasaporte: ");	
		informacionDeVisa[0] = vista.leerString("Digite sus dias de estadía en el país: (Presione enter antes de ingresar el dato)");
		String opcionAcompanantes = vista.leerString(" ï¿½Va a viajar sin acompañante? Selecione SI/NO :(Presione enter antes de ingresar el dato)");
		do
		{
			try
			{	
				String[] datosTipoDeVisa = Manejo_Archivos.leerDatosVisa(embajada, "tarifas", "turismo");
				String idVisa = datosTipoDeVisa[0];
				double tarifa = Double.valueOf(datosTipoDeVisa[2]);
				if(!opcionAcompanantes.equalsIgnoreCase("si") && !opcionAcompanantes.equalsIgnoreCase("no"))
				{
					vista.imprimir("SELECCIONE UNA OPCION CORRECTA ");
				}
				else
				{
					if(opcionAcompanantes.equalsIgnoreCase("si"))	
					{
						numerosDePasaporte.add(numeroPasaporte);
					}
					if(opcionAcompanantes.equalsIgnoreCase("no"))	
					{
						String nombreArchivo = vista.leerString(" Escriba el nombre del archivo con los numeros de pasaporte de los acompaï¿½antes (Presione enter antes de ingresar el dato)");
						numerosDePasaporte = Manejo_Archivos.leerAcompanantes(embajada, nombreArchivo);
						numerosDePasaporte.add(0,numeroPasaporte);
					}
					embajada.solicitarSolicitud(numerosDePasaporte,"turismo", tarifa, idVisa, informacionDeVisa);
					vista.mostrarRecuadro("SE HA CREADO LA SOLICITUD");
				}
			}
			catch(NullPointerException e)
			{
				vista.mostrarError("No se pudo generar la solicitud "+e.getMessage());
			}
			catch(IOException e)
			{
				vista.mostrarError(e.getMessage());
			}
			
		}
		while(!opcionAcompanantes.equalsIgnoreCase("si") && !opcionAcompanantes.equalsIgnoreCase("no"));
		
	}
	/**
	 * Permite generar una solicitud de tipo visa diferente a turismo, valida para un solo usuario
	 * @return void
	 */
	/*Trabajo -->  informacionVisa[0]=empresa   informaciónVisa[1]=cargo
	  Estudiante-> informacionVisa[0]=Institución   informaciónVisa[1]=escolaridad
	*/
	public void hacerSolicitudNoTurismo()
	{	
		List<String> numerosDePasaporte = new ArrayList<String>();
		String[] informacionDeVisa = new String[2];
		String numeroPasaporte = vista.leerString(" Digite su numero de Pasaporte: ");
		
		vista.imprimir("Tipos de Visas");
		vista.imprimir("Trabajo");
		vista.imprimir("Estudiante");
		
		String tipoVisa = vista.leerString(" Digite el tipo de visa (Presione enter antes de ingresar el dato): ");
		try
		{	
			String[] datosTipoDeVisa = Manejo_Archivos.leerDatosVisa(embajada, "tarifas", tipoVisa);
			String idVisa = datosTipoDeVisa[0];
			double tarifa = Double.valueOf(datosTipoDeVisa[2]);
			
			if(tipoVisa.equalsIgnoreCase("trabajo"))
			{
				informacionDeVisa[0] = vista.leerString(" Digite el nombre de la empresa donde va a laborar: (Presione enter antes de ingresar el dato)");
				informacionDeVisa[1] = vista.leerString(" Digite el cargo que va a desempeñar: (Presione enter antes de ingresar el dato)");
			}
			else if(tipoVisa.equalsIgnoreCase("estudiante"))
			{
				informacionDeVisa[0] = vista.leerString(" Digite el nombre de la Institución en la cula va a estudiar: (Presione enter antes de ingresar el dato)");
				informacionDeVisa[1] = vista.leerString(" Digite el nivel de escolaridad a realizar:(Presione enter antes de ingresar el dato) ");
			}
			numerosDePasaporte.add(numeroPasaporte);
			embajada.solicitarSolicitud(numerosDePasaporte,tipoVisa,tarifa,idVisa,informacionDeVisa );
			vista.mostrarRecuadro("SE HA CREADO LA SOLICITUD");
		}
		catch(NullPointerException e)
		{
			vista.mostrarError("No pudo generar la solicitud "+e.getMessage());
		}
		catch(IOException e)
		{
			vista.mostrarError(e.getMessage());
		}
	}
	/**
	 * Genera un reporte de el valor a pagar por la visa de uno o varios usuarios dependiendo si el usuario ingresó el numero de pasaporte o el código de la solicitud 
	 *@return void
	 * */
	 public void calcularValorVisa()
	 {
		try
		{
		 List<String[]> valoresVisa = new ArrayList<String[]>();
		 vista.imprimir("Calcular valor de visa para la embajada de "+embajada.getPaisEmbajada());
		 String numero = vista.leerString("Ingrese su numero de pasaporte o numero de solicitud: (Emplee las iniciales NP(numero pasaporte) o CP(codigo solicitud) para indicar dato ingresado) ");
		
		 valoresVisa = embajada.calcularVisaXSolicitud(numero);
		 float valorTotalVisa = 0;
		
		 vista.imprimir("#numPass—----nombre----------fechaNac---valorVisa-------impuesto------valorTotal");
		 for(String[] valorVisaUsuario : valoresVisa)
		 {
			 valorTotalVisa+= Float.valueOf(valorVisaUsuario[5]);
			 vista.imprimir(valorVisaUsuario[0]+"------"+valorVisaUsuario[1]+"------"+valorVisaUsuario[2]+"------"+valorVisaUsuario[3]+"------"+valorVisaUsuario[4]+"------"+valorVisaUsuario[5]+"-----");
		 }
		 
		 vista.imprimir("El valor total de la visa es:  ");
		 vista.imprimir("---Ps------------Tasa de cambio-----------"+embajada.getMoneda());
		 vista.imprimir("--"+valorTotalVisa+"---------"+embajada.getCambioOficial()+"----------"+embajada.getMoneda()+" "+valorTotalVisa*embajada.getCambioOficial());
		 }
	 	catch (NullPointerException e) {
			vista.imprimir(" Debe indicar si es número de pasaporte o código de solicitud");
		}
	 }
	/**
	 * Genera un reporte de las solicitudes que se encuentran programadas para una fecha en especifico, a traves de la 
	 * creacion de un archivo que muestra las solicitudes programadas para esa fecha junto con los usarios respectivos de cada solicitud
	 * @return void
	 */
	public void generarReporteCitasFecha() 
	{
		String fecha = vista.leerString(" Ingrese la fecha para el reporte (yyyy-mm-dd): ");
		List<List<String>> usuarios = new ArrayList<List<String>>();

		try
		{	
			usuarios = embajada.buscarCitasFecha(fecha);
			Manejo_Archivos.escribirReporteCitasFecha(fecha, usuarios,embajada.getPaisEmbajada().toUpperCase());

		}
		catch(NullPointerException e)
		{
			vista.mostrarError("No hay citas para esa fecha"+e.getMessage());
		}
		catch(IOException e)
		{
			vista.mostrarError(e.getMessage());
		}
	}
	/**
	 * Permite consultar los requisitos necesarios para un tipo de visa existente en especifico  
	 * @return void
	 */
	public void consultarTipoVisa()
	{
		List<List<String>> visas = new ArrayList<List<String>>();
		try
		{
			
			String nombreArchivo = vista.leerString(" Digite el nombre del archivo con los requisitos de las visa:  ");
			
			vista.imprimir("--Lista de requisitos para Visa");
			vista.imprimir("--Tipos de Visa--");
			vista.imprimir("Trabajo");
			vista.imprimir("Turismo");
			vista.imprimir("Estudiante");

			String visaEscogida = vista.leerString("-----Favor indicar el tipo de visa del que desea conocer los requisitos: -----");
			
			visas = Manejo_Archivos.leerVisas(embajada, nombreArchivo);
			for( List<String> visa : visas)
			{
				
				if(visaEscogida.equalsIgnoreCase(visa.get(0)))
				{
					visa.remove(0);
					for(String  requisito : visa)
					{
						vista.imprimir(requisito);
					}
				}	
			}
		}
		catch(Exception e)
		{
			vista.mostrarError(e.getMessage());
		}
	}
	/**
	* Permite consultar la lista de los beneficiarios que recibieron un descuento en el valor de su visa
	* @return void
	**/
	public void consultarBenefeciarios()
	{
		try {
		List<String[]> usuarios = new ArrayList<String[]>();
		usuarios = embajada.buscarBeneficiarios();
		
			Manejo_Archivos.escribirReporteBeneficiarios(embajada.getCambioOficial(), usuarios, embajada.getMoneda());
		} 
		catch (IOException e) {
			vista.mostrarError(e.getMessage());
		}
	}
	public void imprimirEmbajada()
	{
		vista.imprimir(embajada.getPaisEmbajada());
		vista.imprimir(embajada.getMoneda());
		vista.imprimir(String.valueOf(embajada.getCambioOficial()));
		vista.imprimir(String.valueOf(embajada.getImpuesto()));
	}
}

