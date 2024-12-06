import java.sql.*;
import java.util.Scanner; 

public class crud {

	public static void main(String[] args) {
		String JDBC_DRIVER =  "com.mysql.cj.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/movies";
		String USER = "root";
		String PASS = "";
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			String query = "CREATE TABLE IF NOT EXISTS movies (id INT NOT NULL AUTO_INCREMENT, title VARCHAR(255), director VARCHAR(255), summary VARCHAR(255), genres VARCHAR(225), PRIMARY KEY (id))";
			stmt.executeUpdate(query);
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("1. Create Movies: ");
			System.out.println("2. Show Movies");
			System.out.println("3. Update Movies");
			System.out.println("4. Delete Movies");
			
			System.out.print("enter choice movies: ");
			String choice = scan.nextLine();
			
			switch(choice) {
			case "1":
				System.out.print("judul film: ");
				String title = scan.nextLine();
				
				System.out.print("nama director: ");
				String director = scan.nextLine();
				
				System.out.print("rangkuman cerita: ");
				String summary = scan.nextLine();
				
				System.out.print("genre film: ");
				String genres = scan.nextLine();
				
				query = "INSERT INTO movies (title, director, summary, genres) VALUES ('"+title+"','"+director+"', '"+summary+"', '"+genres+"')";
				 stmt.executeUpdate(query);
				break;
			case "2":
				
				System.out.print("Enter movies: ");
				int id = scan.nextInt();
				
				query = "SELECT * FROM movies WHERE id = "+ id;
				
				ResultSet rs = stmt.executeQuery(query);
				
				if(rs.next()) {
					System.out.print("ID: "+rs.getInt("id"));
					System.out.print(" title: "+rs.getString("title"));
					System.out.print(" director: "+rs.getString("director"));
					System.out.print(" summary: "+rs.getString("summary"));
					System.out.print(" genres: "+rs.getString("genres"));
				} else {
				System.out.println("movies not found!");	
				}
				
				break;
			case "3":
				
				System.out.print("enter movies id: ");
				id = scan.nextInt();
				
				scan.nextLine();
				
				System.out.print("judul film: ");
				title = scan.nextLine();
				
				System.out.print("nama director: ");
				director = scan.nextLine();
				
				System.out.print("rangkuman cerita: ");
				summary = scan.nextLine();
				
				System.out.print("genre film: ");
				genres = scan.nextLine();
				
				query = "UPDATE movies SET title = '"+title+"', director = '"+director+"', summary =  '"+summary+"', genres = '"+genres+"' WHERE id =  " + id;
			
				int result = stmt.executeUpdate(query);
				
				if(result > 0) {
					System.out.println("movies updated successfully!");
				} else {
					System.out.println("movies not found!");
			}
			break;
			case "4":
				System.out.print("Ente movies id: ");
				id = scan.nextInt();
				
				query = "DELETE FROM movies WHERE id = " +id;
				
				result = stmt.executeUpdate(query);
				if (result > 0 ) {
					System.out.println("movies deleted successfully!");
				}else {
					System.out.println("movies not found!");
				}
			}
			stmt.close();
			conn.close();
			
		} catch (Exception e) {
			System.out.print("Error: "+e.getMessage());
		}

	}

}
