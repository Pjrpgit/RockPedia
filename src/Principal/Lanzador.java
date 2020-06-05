package Principal;

import java.sql.SQLException;
import java.text.ParseException;
import vista.Principal;

public class Lanzador {

	public static void main(String[] args) {
		try {
			try {
				Principal frame=new Principal();
				frame.setVisible(true);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
