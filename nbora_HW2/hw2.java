import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class hw2 {
	private String dbhost = "jdbc:mysql://sql3.freemysqlhosting.net/";
	private String dbname = "sql312468";
	private String user = "sql312468";
	private String pass = "cX9%bG8%";

	JTextArea txt_area;
			

	public static void main(String arg[]) {
		ArrayList<String> args = new ArrayList<String>();
		for(int i=0; i < arg.length; i++) {
			args.add(arg[i]);
		}

		hw2 obj = new hw2();
		obj.init_map();
		obj.arg_parser(args);
	}
	
	public hw2() {
		try {
			String dbhost = null, dbname = null, user = null, pass = null;
			
			BufferedReader br = new BufferedReader(new FileReader("db-settings.txt"));
	        String line;
	        
	        while((line = br.readLine()) != null) {	        	
		        String[] pair = line.split("\\s+");
		        
		        if(pair.length == 2) {
		        	if(pair[0].equals("dbhost")) {
		        		dbhost = pair[1];
		        	}
		        	else if(pair[0].equals("dbname")) {
		        		dbname = pair[1];
		        	}
		        	else if(pair[0].equals("user")) {
		        		user = pair[1];
		        	}
		        	else if(pair[0].equals("pass")) {
		        		pass = pair[1];
		        	}
		        }
	        }
	        
	        if(dbhost != null && dbname != null && user != null && pass != null) {
	        	//System.out.println(dbhost + dbname + user + pass);
	        	this.dbhost = dbhost;
	        	this.dbname = dbname;
	        	this.user = user;
	        	this.pass = pass;
	        }
		} catch (Exception e) {
			System.out.println("DB dettings file not found.\n" +
					"Using default settings.\n" +
					"Make sure you are connected to the internet.");
		}
	}
	
	private void arg_parser(ArrayList<String> args) {
		String arg_str = "Command line:\n    ";
		for(int i=0; i<args.size(); i++) {
			arg_str += args.get(i) + " ";
		}
		this.txt_area.append(arg_str);
		this.txt_area.append("\n\nRESULTS:");
		
		String txt = null;
		
		if(args.size() > 0) {
			String query_type = args.remove(0);
			
			if(query_type.equals("window")) {	
				txt = getq_window(args.remove(0), args);
			}
			else if(query_type.equals("within")) {
				txt = getq_within(args.remove(0), args);
			}
			else if(query_type.equals("nn")) {
				txt = getq_nn(args.remove(0), args);
			}
			else if(query_type.equals("demo")) {
				int demo_number = 0;
				try {
					demo_number = Integer.parseInt(args.get(0));
				} catch(Exception e) {
					e.printStackTrace();
					txt += "Invalid parameters!";
				}
				
				switch(demo_number) {
				case 1:
					txt += this.getq_demo1();
					break;
				case 2:
					txt += this.getq_demo2();
					break;
				case 3:
					txt += this.getq_demo3();
					break;
				case 4:
					txt += this.getq_demo4();
					break;
				case 5:
					txt += this.getq_demo5();
					break;
				default:
					txt += "Invalid parameters!";
					break;
				}
			}
			else { txt = "Invalid parameters!"; }
			
			//this.txt_area.append(txt);
		}
	}
	
	/*
	 * WINDOW query
	 */
	private String getq_window(String object_type, ArrayList<String> other_param) {
		String txt = "";
		String pol_wkt = null;
		String query = null;
		try {
			int sw_x = Integer.parseInt(other_param.get(0));
			int sw_y = Integer.parseInt(other_param.get(1));
			int ne_x = Integer.parseInt(other_param.get(2));
			int ne_y = Integer.parseInt(other_param.get(3));
			pol_wkt = "POLYGON((" + sw_x + " " + sw_y + ", " +
										sw_x + " " + ne_y + ", " +
										ne_x + " " + ne_y + ", " +
										ne_x + " " + sw_y + ", " +
										sw_x + " " + sw_y + "))";
		} catch(Exception e) {
			e.printStackTrace();
			txt += "Invalid parameters!";
		}
		
		
		if(object_type.equals("building")) {
			query = "SELECT b_id AS id " +
					"FROM building " +
					"WHERE Within(pol, GeomFromText('"+ pol_wkt +"')) " +
							"AND fire=0;";
		}
		else if(object_type.equals("firehydrant")) {
			query = "SELECT h_id AS id " +
					"FROM hydrant " +
					"WHERE Within(geo, GeomFromText('"+ pol_wkt +"'));";
		}
		else if(object_type.equals("firebuilding")) {
			query = "SELECT b_id AS id " +
					"FROM building " +
					"WHERE Within(pol, GeomFromText('"+ pol_wkt +"')) " +
							"AND fire=1;";
		}
		else { txt += "Invalid parameters!"; }
		
		if(query != null) {
			try {
				ResultSet rs = this.exec_query(query);
				while(rs.next()) {
					txt += "\n" + rs.getString("id");
				}
			} catch (SQLException e) {
				System.out.println("SQL Error!");
				e.printStackTrace();
			}
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	/*
	 * WITHIN query
	 */
	private String getq_within(String object_type, ArrayList<String> other_param) {
		String txt = "";
		String building_name = null;
		int dist = 0;
		String query = null;
		try {
			building_name = other_param.get(0);
			dist = Integer.parseInt(other_param.get(1));
		} catch(Exception e) {
			e.printStackTrace();
			txt += "Invalid parameters!";
		}
		
		if(object_type.equals("building")) {
			query = "SELECT b_id as id " +
					"FROM building " +
					"WHERE fire=0 " +
					"AND glength( " +
					 	"LineStringFromWKB(LineString( " +
							"Centroid(pol), (SELECT Centroid(pol) " +
											"FROM building " +
											"WHERE name='"+ building_name +"' )))) < "+ dist +";";
		}
		else if(object_type.equals("firehydrant")) {
			query = "SELECT h_id as id " +
					"FROM hydrant " +
					"WHERE glength( " +
					 	"LineStringFromWKB(LineString( " +
							"geo, (SELECT Centroid(pol) " +
								"FROM building " +
								"WHERE name='"+ building_name +"' )))) < "+ dist +";";
		}
		else if(object_type.equals("firebuilding")) {
			query = "SELECT b_id as id " +
					"FROM building " +
					"WHERE fire=1 " +
					"AND glength( " +
					 	"LineStringFromWKB(LineString( " +
							"Centroid(pol), (SELECT Centroid(pol) " +
											"FROM building " +
											"WHERE name='"+ building_name +"' )))) < "+ dist +";";
		}
		else { txt += "Invalid parameters!"; }
		
		if(query != null) {
			try {
				ResultSet rs = this.exec_query(query);
				while(rs.next()) {
					txt += "\n" + rs.getString("id");
				}
			} catch (SQLException e) {
				System.out.println("SQL Error!");
				e.printStackTrace();
			}
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	/*
	 * NEAREST NEIGHBOR query
	 */
	private String getq_nn(String object_type, ArrayList<String> other_param) {
		String txt = "";
		String building_id = null;
		int k = 0;
		String query = null;
		try {
			building_id = other_param.get(0);
			k = Integer.parseInt(other_param.get(1));
		} catch(Exception e) {
			e.printStackTrace();
			txt += "Invalid parameters!";
		}
		
		if(object_type.equals("building")) {
			query = "SELECT b1.b_id as id, glength( LineStringFromWKB(LineString( " +
										"Centroid(b1.pol), b2.center))) as dist " +
					"FROM building b1, (SELECT b_id, Centroid(pol) as center " +
										"FROM building " +
										"WHERE b_id='"+ building_id +"' ) as b2 " +
					"WHERE b1.b_id <> b2.b_id " +
					"AND b1.fire=0 " +
					"ORDER BY dist ASC " +
					"LIMIT "+ k +";";
		}
		else if(object_type.equals("firehydrant")) {
			query = "SELECT h.h_id as id, glength( LineStringFromWKB(LineString( " +
										"geo, b.center))) as dist " +
					"FROM hydrant h, (SELECT b_id, Centroid(pol) as center " +
										"FROM building " +
										"WHERE b_id='"+ building_id +"' ) as b " +
					"ORDER BY dist ASC " +
					"LIMIT "+ k +";";
		}
		else if(object_type.equals("firebuilding")) {
			query = "SELECT b1.b_id as id, glength( LineStringFromWKB(LineString( " +
										"Centroid(b1.pol), b2.center))) as dist " +
					"FROM building b1, (SELECT b_id, Centroid(pol) as center " +
										"FROM building " +
										"WHERE b_id='"+ building_id +"' ) as b2 " +
					"WHERE b1.b_id <> b2.b_id " +
					"AND b1.fire=1 " +
					"ORDER BY dist ASC " +
					"LIMIT "+ k +";";
		}
		else { txt += "Invalid parameters!"; }
		
		if(query != null) {
			try {
				ResultSet rs = this.exec_query(query);
				while(rs.next()) {
					txt += "\n" + rs.getString("id");
				}
			} catch (SQLException e) {
				System.out.println("SQL Error!");
				e.printStackTrace();
			}
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	/*
	 * DEMO queries
	 */
	private String getq_demo1() {
		this.txt_area.append("\n\nRunning... Please wait!\n");
		String txt = "\nBuildings on fire, or < 100m of them:";
		String query = "SELECT name " +
				"FROM building " +
				"WHERE fire=1 " +
				"UNION " +
				"SELECT b1.name AS name " +
				"FROM building b1, (SELECT b_id, Centroid(pol) as center " +
									"FROM building " +
									"WHERE fire=1 ) as b2 " +
				"WHERE b1.b_id <> b2.b_id " +
				"AND glength( LineStringFromWKB(LineString( " +
					"Centroid(b1.pol), b2.center))) <= 100; ";
		try {
			ResultSet rs = this.exec_query(query);
			while(rs.next()) {
				txt += "\n" + rs.getString("name");
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	private String getq_demo2() {
		this.txt_area.append("\n\nRunning... Please wait!\n");
		String txt = "\n5 nearest Fire-hydrants to each Buildings on fire:";
		String query1 = "SELECT b_id AS id " +
				"FROM building " +
				"WHERE fire=1;";
		ArrayList<String> h_ids = new ArrayList<String>();
		
		try {
			ResultSet rs = this.exec_query(query1);
			while(rs.next()) {				
				String building_id = rs.getString("id");
				
				String query2 = "SELECT h.h_id as id, glength( " +
										"LineStringFromWKB(LineString( " +
											"h.geo, b.center))) as dist " +
								"FROM hydrant h, (SELECT b_id, Centroid(pol) as center " +
												"FROM building " +
												"WHERE b_id='"+ building_id +"' ) as b " +
								"ORDER BY dist ASC " +
								"LIMIT 5;";
				
				ResultSet rs2 = this.exec_query(query2);
				while(rs2.next()) {
					String h_id = rs2.getString("id");
					if(h_ids.indexOf(h_id) == -1) {
						h_ids.add(h_id);
						txt += "\n" + h_id;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	private String getq_demo3() {
		this.txt_area.append("\n\nRunning... Please wait!\n");
		String txt = "";
		String query1 = "SELECT b_id AS id " +
				"FROM building " +
				"WHERE fire=1;";
		ArrayList<String> h_ids = new ArrayList<String>();
		
		try {
			ResultSet rs = this.exec_query(query1);
			while(rs.next()) {				
				txt = "";
				
				String building_id = rs.getString("id");
				txt += "\n\nBuilding on Fire: " + building_id;
				
				String query2 = "SELECT h.h_id as id, glength( " +
												"LineStringFromWKB(LineString( " +
													"h.geo, b.center))) as dist " +
						"FROM hydrant h, (SELECT b_id, Centroid(pol) as center " +
										"FROM building " +
										"WHERE b_id='"+ building_id +"' ) as b " +
						"ORDER BY dist ASC " +
						"LIMIT 1;";
				
				ResultSet rs2 = this.exec_query(query2);
				if(rs2.next()) {
					String h_id = rs2.getString("id");
					txt += "\nNearest Fire-hydrant: " + h_id;
					
					String query3 = "SELECT b.name AS name " +
							"FROM building b, (SELECT geo " +
											"FROM hydrant " +
											"WHERE h_id='"+ h_id +"') AS h " +
							"WHERE glength( LineStringFromWKB(LineString( " +
										"Centroid(b.pol), h.geo))) <= 100; ";
					
					ResultSet rs3 = this.exec_query(query3);
					txt += "\nBuildings within 100m of "+ h_id +": ";
					while(rs3.next()) {
						txt += "\n" + rs3.getString("name");
					}
				}
				
				this.txt_area.append(txt);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
		
		return txt;
	}
	
	private String getq_demo4() {
		this.txt_area.append("\n\nRunning... Please wait!\n");
		String txt = "\nBuilding(s) neaest to Fire-hydrant p30: ";
		ArrayList<String> blds = this.get_nearest_buildings("p30");
		
		for(int i=0; i<blds.size(); i++) {
			txt += "\n" + blds.get(i);
		}
		
		this.txt_area.append(txt);
		return txt;
	}
	
	private String getq_demo5() {
		this.txt_area.append("\n\nRunning... Please wait!\n");
		String txt = ""; 
		this.txt_area.append("\n<Fire-hydrant ID, # of buildings>");
		String query1 = "SELECT h_id AS id " +
				"FROM hydrant;";
		
		try {
			ResultSet rs = this.exec_query(query1);
			while(rs.next()) {				
				String h_id = rs.getString("id");
				
				ArrayList<String> blds = this.get_nearest_buildings(h_id);
				
				this.txt_area.append("\n" + h_id + ": " + blds.size());
				txt += "\n" + h_id + ": " + blds.size();
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
					
		return txt;
	}
	
	private ArrayList<String> get_nearest_buildings(String h_id) {
		ArrayList<String> blds = new ArrayList<String>();
		String query1 = "SELECT b_id AS id, name " +
				"FROM building;";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}
		
		try {
			Connection con = DriverManager.getConnection(this.dbhost + this.dbname, 
					this.user, this.pass);
			if (con != null) {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(query1);
				while(rs.next()) {				
					String b_id = rs.getString("id");
					String hyd = this.get_nearest_hydrant(b_id);
					
					if(hyd.equals(h_id)) {
						blds.add(rs.getString("name"));
					}
				}
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
		
		return blds;
	}
	
	private String get_nearest_hydrant(String b_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}
		
		String h_id = null;
		String query = "SELECT h.h_id as id, glength( " +
							"LineStringFromWKB(LineString( " +
								"h.geo, b.center))) as dist " +
			"FROM hydrant h, (SELECT b_id, Centroid(pol) as center " +
					"FROM building " +
					"WHERE b_id='"+ b_id +"' ) as b " +
			"ORDER BY dist ASC " +
			"LIMIT 1;";
		
		try {
			Connection con = DriverManager.getConnection(this.dbhost + this.dbname, 
					this.user, this.pass);
			if (con != null) {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(query);
				
				if(rs.next()) {
					h_id = rs.getString("id");
				}
				con.close();
			}
		} catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}
		
		return h_id;
	}
	
	
	

	/*
	 * GUI panels
	 */
	private void init_map() {
		/*
		 * Get building polygons
		 */
		ArrayList<Polygon> pols = new ArrayList<Polygon>();
		ArrayList<Polygon> pols_fire = new ArrayList<Polygon>();
		String query = "SELECT fire, AsText(pol) " +
				"FROM building;";
		try {
			ResultSet rs = this.exec_query(query);

			while(rs.next()) {
				String pol = rs.getString("AsText(pol)");
				int fire = rs.getInt("fire");
				Polygon poly = new Polygon();

				Pattern p = Pattern.compile("\\d*\\s+\\d*");
				Matcher m = p.matcher(pol);
				while (m.find()) {
					String latlng = m.group(); 
					poly.addPoint(Integer.parseInt(latlng.split(" ")[0]), 
							Integer.parseInt(latlng.split(" ")[1]));
				}

				if(fire==0) {
					pols.add(poly);
				}
				else {
					pols_fire.add(poly);
				}
			}
		}
		catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}

		/*
		 * Get hydrant coordinates
		 */
		ArrayList<Point> pts = new ArrayList<Point>();
		query = "SELECT AsText(geo)" +
				"FROM hydrant;";
		try {
			ResultSet rs = this.exec_query(query);

			while(rs.next()) {
				String pt_wkt = rs.getString("AsText(geo)");

				Pattern p = Pattern.compile("\\d*\\s+\\d*");
				Matcher m = p.matcher(pt_wkt);
				m.find();
				String latlng = m.group();
				Point pt = new Point(Integer.parseInt(latlng.split(" ")[0]), 
						Integer.parseInt(latlng.split(" ")[1]));

				pts.add(pt);
			}
		}
		catch (SQLException e) {
			System.out.println("SQL Error!");
			e.printStackTrace();
		}


		/*
		 * MAP panel
		 */		
		Image img = new ImageIcon("map.jpg").getImage();
		ImagePanel mapp = new ImagePanel(img, pols, pols_fire, pts);
		mapp.setPreferredSize(new Dimension(820, 580));
		mapp.setMinimumSize(new Dimension(820, 580));

		/*
		 * CONSOLE panel
		 */
		this.txt_area = new JTextArea();
		this.txt_area.setFont(new Font("MONOSPACED", Font.BOLD, 14));
		this.txt_area.setBackground(Color.DARK_GRAY);
		this.txt_area.setForeground(Color.CYAN);
		this.txt_area.setEditable(false);
		this.txt_area.setLineWrap(true);
		this.txt_area.setWrapStyleWord(true);

		JScrollPane conp = new JScrollPane(this.txt_area);
		conp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		conp.setPreferredSize(new Dimension(350, 550));
		conp.setMinimumSize(new Dimension(350, 580));
		conp.setBackground(Color.DARK_GRAY);
		conp.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Console"),
				BorderFactory.createEmptyBorder(5,5,5,5)));

		/*
		 * Create JFrame
		 */
		JPanel left = new JPanel(new BorderLayout());
		left.add(mapp, BorderLayout.PAGE_START);

		JPanel right = new JPanel(new GridLayout(1,0));
		right.add(conp);

		JFrame frm = new JFrame("USC Fire Department");
		frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frm.setSize(1170, 600);
		//frm.pack();
		frm.setResizable(false);
		frm.add(left, BorderLayout.LINE_START);
		frm.add(right, BorderLayout.LINE_END);
		frm.setVisible(true);
	}
	
	/*
	 * QUERY executer
	 */
	private ResultSet exec_query(String query) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}

		try {
			Connection con = DriverManager.getConnection(this.dbhost + this.dbname, 
														this.user, this.pass);
			if (con != null) {
				Statement stm = con.createStatement();
				ResultSet rs = stm.executeQuery(query);
				
				return rs;
			}
		}
		catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
		}
		return null;
	}
};


/*
 * IMAGE Panel class
 */
class ImagePanel extends JPanel {

	private Image img;
	private ArrayList<Polygon> poly;
	private ArrayList<Polygon> poly_fire;
	private ArrayList<Point> pts;

	public ImagePanel(Image img, 
			ArrayList<Polygon> poly, 
			ArrayList<Polygon> poly_fire,
			ArrayList<Point> pts) {
		this.img = img;
		this.poly = poly;
		this.poly_fire = poly_fire;
		this.pts = pts;
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);

		g.setColor(Color.GREEN);
		for(int i=0; i<poly.size(); i++) {
			g.drawPolygon(poly.get(i));
		}

		g.setColor(Color.RED);
		for(int i=0; i<poly_fire.size(); i++) {
			g.drawPolygon(poly_fire.get(i));
		}

		g.setColor(Color.YELLOW);
		for(int i=0; i<pts.size(); i++) {
			Point pt = pts.get(i);
			g.fillRect(pt.x, pt.y, 5, 5);
		}
	}

	public Dimension getPreferredSize() {
		return new Dimension(820, 580);
	}
}