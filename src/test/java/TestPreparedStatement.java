import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TestPreparedStatement {

    public static void main(String[] args) {
         // UseNativeQuery=1
        String connectionString = "jdbc:impala://[MY_IP]:[MY_PORT];UseNativeQuery=1;AuthMech=3";
        String username = "username";
        String password = "password";

        try {
            Class.forName("com.cloudera.impala.jdbc4.Driver");

            Connection _conn = DriverManager.getConnection(connectionString, username, password);

            String _sql = "select number_impala, string_impala from my_table where number_impala = ?";

            PreparedStatement pstm = _conn.prepareStatement(_sql);

            pstm.setObject(1, 1);

            pstm.executeQuery();

            pstm.close();

            _conn.close();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TestPreparedStatement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
