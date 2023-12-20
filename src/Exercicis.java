
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class Exercicis {

	public static void main(String[] args) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase database = mongoClient.getDatabase("FeidSongs");
		MongoCollection<Document> coleccionLlibres = database.getCollection("Songs");

		// Insert(coleccionLlibres);
		// Update(coleccionLlibres);
		// Delete(coleccionLlibres);
		Select(coleccionLlibres);

		mongoClient.close();

	}

	public static void Insert(MongoCollection<Document> coleccionLlibres) {
		Document document = new Document();
		document.append("name", "Buenas");
		document.append("Year", 2020);
		coleccionLlibres.insertOne(document);

		// coleccion.insertMany(listaDocuments);

	}

	public static void Select(MongoCollection<Document> coleccionLlibres) {
		MongoCursor<Document> cursor = coleccionLlibres.find().iterator();
		while (cursor.hasNext()) {
			System.out.println(cursor.next().toJson());
		}

		// JSONObject obj = new JSONObject(cursor.next().toJson());
		// System.out.println(obj.getString("name"));

	}

	public static void Update(MongoCollection<Document> coleccionLlibres) {
		// Actualica la primera que trobe
		coleccionLlibres.updateOne(eq("name", "Buenas"), new Document("$set", new Document("Year", "2023")));

		// Actualica totes
		// coleccion.updateMany(eq("formato", "WAV"), new Document("$set", new
		// Document("formato", "OGG")));

	}

	public static void Delete(MongoCollection<Document> coleccionLlibres) {
		// Eliminar la primrea que trobe
		coleccionLlibres.deleteOne(eq("name", "Buenas"));

		// Elimina totes amb eixe filtre
		// coleccion.deleteMany(eq("formato", "OGG"));

		// Elimina tot !CUIDADO!!
		//
		// coleccionLlibres.drop();

	}
}
