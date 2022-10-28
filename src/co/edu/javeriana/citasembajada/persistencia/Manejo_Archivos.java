package co.edu.javeriana.citasembajada.persistencia;
/**
 * @author Ricardo Riscanevo-Juan Sebastian Solano
 **/
import java.io.BufferedReader;
import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import co.edu.javeriana.citasembajada.modelo.SistemaCitas_Embajada;


public class Manejo_Archivos {

	/** 
	 * 
	 * Retorna los datos de la embajada de un país especificada por el usuario buscada dentro de un archivo, ingresando al archivo a través del nombre especificado por el usuario
	 * @param String nombreArchivo
	 * @param String nombrePais
	 * @param SistemaCitas_Embajada modelo
	 * @return String[] datos de embajada                   
	 * @return String[0]Id     String[1] paisEmbajada     String[2] moneda    String[3] impuesto    String[4] cambioOficial
	 * @throws Exception excepcion en la apertura del archivo
	 **/
	public static String[] leerEmbajada (String nombreArchivo, SistemaCitas_Embajada modelo, String nombrePais) throws Exception
	{
		boolean encontro = false;
		BufferedReader br = new BufferedReader(new FileReader("./"+nombreArchivo+".txt"));
		String linea = br.readLine();
		String[] datos = null;

		while(linea.equals("#FIN") == false)
		{	
			datos = linea.split("\\*");
			if(linea.startsWith("#")==false)
			{
				if(nombrePais.equalsIgnoreCase(datos[1].trim()) == true)
				{
					datos[0] = datos[0].trim();
					datos[1] = datos[1].trim();
					datos[2] = datos[2].trim();
					String [] datos2 = datos[3].split("%");
					datos[3] = datos2[0].trim();
					datos[4] = datos[4].trim();
					encontro = true;
					break;
				}	
			}
			linea = br.readLine();
		}
		br.close();
		if(encontro == false)
		{
			throw new NullPointerException("No se encuentra el pais ingresado");
		}
		return datos;
	}
	/** 
	 * 
	 * Retorna en una lista los datos de los usuarios guardados dentro de un archivo, ingresando al archivo a través del nombre especificado por el usuario
	 * @param String nombreArchivo
	 * @param SistemaCitas_Embajada modelo
	 * @return List<String[]>
	 * @return String[0] numero Pasaporte	String[1] nombre	String[2] pais Origen	String[3] ciudad Naciomiento	String[4] fecha Nacimiento	String[5] email	String[6] informacion Adicional
	 * @throws IOException excepcion en la apertura del archivo
	 **/

	public static List<String[]> leerSolicitantes(String nombreArchivo, SistemaCitas_Embajada modelo) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("./"+nombreArchivo+".txt"));
		List<String[]> datosUsuarios = new ArrayList<String[]>();
		
		String linea = br.readLine();
		while(linea.equals("#FIN") == false)
		{
			if(linea.startsWith("#") == false)
			{
				String[] datos = linea.split ("\\*");
				
				datos[0] = datos[0].trim();
				datos[1] = datos[1].trim();
				datos[2] = datos[2].trim();
				datos[3] = datos[3].trim();
				datos[4] = datos[4].trim();
				datos[5] = datos[5].trim();
				if(datos.length == 7)
				{
					datos[6] = datos[6].trim();
				}
				datosUsuarios.add(datos);
			}
			linea = br.readLine();
		}
		br.close();
		return datosUsuarios;
	}
	
	/** 
	 * Retorna una lista de las visas con su respectiva lista de requerimientos buscadas dentro de un archivo, ingresando al archivo a través del nombre especificado por el usuario
	 * @param String nombreArchivo
	 * @param SistemaCitas_Embajada modelo
	 * @return  List<List<String>>
	 * @return List<String>.get(0) = nombre Visa		List<String>.get(>0) = requisitos Visa
	 * @throws Exception excepcion en la apertura del archivo
	 **/
	public static List<List<String>> leerVisas (SistemaCitas_Embajada modelo, String nombreArchivo) throws IOException
	{	
		BufferedReader br = new BufferedReader(new FileReader("./"+nombreArchivo+".txt"));
		String linea = br.readLine();
		List<List<String>> visas = new ArrayList<List<String>>();
		while ( linea.equals("#FIN") == false)
		{	
			List<String> datosVisa = new ArrayList<String>();
			
			while(linea.equals("#REQUISITOS")==false)
			{
				if(linea.startsWith("#") == false)
				{
					String[] datos = linea.split("\\*");
					datosVisa.add(datos[0].trim());	
				}
				linea = br.readLine();
			}
			linea = br.readLine();
			while(linea.startsWith("#" )== false)
			{
				datosVisa.add(linea);
				linea=br.readLine();
			}
			visas.add(datosVisa);
		}

		br.close();
		return visas;
	}
	/** 
	 * Retorna un String[] con los datos de la tarifa de una visa específica buscada dentro de un archivo, ingresando al archivo a través del nombre especificado por el usuario
	 * @param String nombreArchivo
	 * @param SistemaCitas_Embajada modelo
	 * @param String nombreVisa
	 * @return String[0] id Visa	String[1] nombre Visa	String[2] tarifa
	 * @throws IOException 
	 **/
	public static String[] leerDatosVisa( SistemaCitas_Embajada modelo, String nombreArchivo, String nombreVisa)throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader("./"+ nombreArchivo +".txt"));
	
		boolean encontro = false;
		String linea = br.readLine();
		String[] datos = new String[3];
		
		while( linea.equalsIgnoreCase("#FIN") == false)
		{
			if(linea.startsWith("#") == false)
			{
				datos = linea.split("\\*");
				if(nombreVisa.equalsIgnoreCase(datos[1].trim()))
				{
					datos[0] = datos[0].trim();
					datos[1] = datos[1].trim();
					datos[2] = datos[2].trim();
					encontro = true;
					break;
				}
			}
			linea = br.readLine();
		}
		br.close();
		if(encontro == false)
		{
			throw new NullPointerException("No se encuentra la tarifa de la visa");
		}
		return datos;
		
	}
	/** 
	 * Retorna una lista con el número de pasaporte de cada uno de los usuarios acompañantes buscado dentro de un archivo,
	 * @param SistemaCitas_Embajada modelo
	 * @param String nombreArchivo
	 * @return List<String>
	 * @throws IOException excepcion en la apertura del archivo
	 **/
	public static List<String> leerAcompanantes(SistemaCitas_Embajada modelo,String nombreArchivo) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader("./"+nombreArchivo+".txt"));
		List<String> datos = new ArrayList<String>();
		String linea = br.readLine();
		while(linea.equalsIgnoreCase("#FIN")== false)
		{
			if(linea.startsWith("#") == false)
			{
				datos.add(linea.trim());
			}
			linea=br.readLine();
		}
		br.close();
		return datos;
	}

	/** 
	 * Genera un reporte de las solicitudes de la embajada de una fecha en específico, creando un archivo en el cual va a escribir los datos suministrados de las solicitudes y los usuarios  ingresando al archivo a través del nombre especificado por el usuario
	 * @param List<String>datosUsuario
	 * @param String fechaCitas
	 * @param String nombreEmbajada
	 * @throws IOException excepcion en la apertura del archivo
	 **/
	public static void escribirReporteCitasFecha(String fechaCitas,List<List<String>> datosUsuario,String nombreEmbajada) throws IOException
	{
		PrintWriter pw = new PrintWriter(new FileWriter("./Citas-"+fechaCitas+".txt"));
		pw.println("--REPORTE DE SOLICITUDES EMBAJADA DE "+nombreEmbajada);
		pw.println("Fecha: "+ fechaCitas);
		pw.println("#numPass--------nombre------------------tipoVisa-----------numSolicitud");
		for(int i=0 ; i<datosUsuario.size() ; i++)
		{
			pw.println(datosUsuario.get(i).get(0)+"  "+datosUsuario.get(i).get(1)+"		"+datosUsuario.get(i).get(2)+"		"+datosUsuario.get(i).get(3));
		}
		pw.close();

	}
	
	public static void escribirReporteBeneficiarios(double cambioOficial,List<String[]> datosUsuario,String moneda) throws IOException 
	{
		PrintWriter pw = new PrintWriter(new FileWriter("./Beneficiarios.txt"));
		pw.println("--Lista de beneficiarios--");
		pw.println("#numPass--------nombre--------valorTotal("+moneda+")");
		for(String[] datos : datosUsuario)
		{
			
			pw.println(datos[0]+"  "+datos[1]+"		"+datos[2]);
		}
		double valorTotal = 0; 
		for(String[] usuario : datosUsuario)
		{
			valorTotal+= Double.valueOf(usuario[2]);
		}
		pw .println("El valor total en pesos que se dejó de recaudar por visas de beneficiados:");
		pw.println( valorTotal+"      Cambio oficial:"+moneda+ " " + (valorTotal*cambioOficial));
		pw.println(moneda+ " " + valorTotal);
		pw.close();

	}
	public static void escribirSerializado(SistemaCitas_Embajada obj) throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./serializacion.obj"));
		oos.writeObject(obj);
		oos.close();
	}
	public static SistemaCitas_Embajada leerSerializado()throws Exception 
	{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./serializacion.obj"));
		SistemaCitas_Embajada objLeido = (SistemaCitas_Embajada)ois.readObject();
		ois.close();
		return objLeido;
	}
}