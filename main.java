import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Obtendo os dados do formulário
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");

        // Conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/seu_banco_de_dados";
        String user = "seu_usuario";
        String password = "sua_senha";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            // SQL para inserir dados no banco
            String sql = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, email);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("<h1>Registro realizado com sucesso!</h1>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
