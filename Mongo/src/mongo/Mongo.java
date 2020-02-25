package mongo;

import org.bson.*;
import com.mongodb.client.*;

import jdk.internal.dynalink.beans.StaticClass;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.zip.InflaterInputStream;

public class Mongo 
{


	public static void main(String[] args) throws IOException 
	{

		int numero;

		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = conexion.getDatabase("HarryPotter");
		MongoCollection<Document> personajesCollection = database.getCollection("Personajes");

		do {
			String recoge_teclado;
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			imprime_menu();
			recoge_teclado = bufferedReader.readLine();
			numero = Integer.parseInt(recoge_teclado);	


			switch (numero) 
			{
			case 1:
				consultarDocumentos(personajesCollection, "species", "human");
				break;

			case 2:
				consultarDocumentos(personajesCollection, "yearOfBirth", 1979);
				break;

			case 3:
				consultarDocumentos(personajesCollection, "wand", "wood", "holly");
				break;

			case 4:
				consultarDocumentos(personajesCollection, "alive", true, "hogwartsStudent", true);
				break;

			}

		} while (numero>=0 && numero<=5);

	}

	public static void imprime_menu()
	{

		try 
		{
			Thread.sleep(1000);
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		System.out.println("------MEN�------");
		System.out.println("OPCI�N 1.");
		System.out.println("OPCI�N 2.");
		System.out.println("OPCI�N 3.");
		System.out.println("OPCI�N 4.");

	}


	public static void consultarDocumentos(MongoCollection<Document> personajes, String atributo1, String valor1) 
	{

		/* Buscar todos documentos de la colecci�n que cumpla los atributos */
		FindIterable<Document> documento = personajes.find(eq(atributo1, valor1));

		/* Recorremos todos los resultados y los mostramos 
		 * Seg�n el objeto colecci�n FindIterable<Document> que ponga, ser� el que
		 * recorra y por tanto ser�n esos elementos los que nos mostrar�
		 */

		for (Document valor : documento) 
		{
			System.out.println(valor.toJson());
		}

	}

	public static void consultarDocumentos(MongoCollection<Document> personajes, String atributo1, int valor1) 
	{

		/* Buscar todos documentos de la colecci�n que cumpla los atributos */
		FindIterable<Document> documento = personajes.find(lte(atributo1, valor1));

		/* Recorremos todos los resultados y los mostramos 
		 * Seg�n el objeto colecci�n FindIterable<Document> que ponga, ser� el que
		 * recorra y por tanto ser�n esos elementos los que nos mostrar�
		 */

		for (Document valor : documento) 
		{
			System.out.println(valor.toJson());
		}

	}

	public static void consultarDocumentos(MongoCollection<Document> personajes, String atributo1, String subatributo1, String valor1) 
	{
		/* Buscar todos documentos de la colecci�n que cumpla los atributos */
		FindIterable<Document> documento = personajes.find(eq(atributo1+"."+subatributo1, valor1));

		/* Recorremos todos los resultados y los mostramos 
		 * Seg�n el objeto colecci�n FindIterable<Document> que ponga, ser� el que
		 * recorra y por tanto ser�n esos elementos los que nos mostrar�
		 */

		for (Document valor : documento) 
		{
			System.out.println(valor.toJson());
		}

	}

	public static void consultarDocumentos(MongoCollection<Document> personajes, String atributo1, boolean valor1, String atributo2, boolean valor2) 
	{

		/* Buscar todos documentos de la colecci�n que cumpla los atributos */
		FindIterable<Document> documento = personajes.find(and(eq(atributo1, valor1),eq(atributo2, valor2)));

		/* Recorremos todos los resultados y los mostramos 
		 * Seg�n el objeto colecci�n FindIterable<Document> que ponga, ser� el que
		 * recorra y por tanto ser�n esos elementos los que nos mostrar�
		 */

		for (Document valor : documento) 
		{
			System.out.println(valor.toJson());
		}

	}

}
