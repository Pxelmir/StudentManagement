import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;

public class MyLogin extends JFrame implements ActionListener{
    JTextField tusername;
    JTextField tpassword;
    JButton login , back, register; 
    
    
   
    MyLogin(){
        JLabel username = new JLabel("username");
        username.setBounds(40,20,100,30);
        add(username);

        tusername = new JTextField();
        tusername.setBounds(110,30,100,20);
        add(tusername);

        JLabel Password = new JLabel("Password");
        Password.setBounds(40,70,100,30);
        add(Password);

        tpassword = new JPasswordField();
        tpassword.setBounds(110,70,100,20);
        add(tpassword);

        login = new JButton("LOGIN");
        login.setBounds(110,100,100,20);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        back = new JButton("Back");
        back.setBounds(210,100,100,20);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        register = new JButton("Register");
        register.setBounds(310, 100, 100, 20);
        register.setBackground(Color.BLACK);
        register.setForeground(Color.white);
        register.addActionListener(this);
        add(register);

    


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/BgLogin.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3= new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 600, 300);
        add(img);

        setSize(600,300);
        setLocation(450, 200);
        setLayout(null);
        setVisible(true);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            try {
                String username = tusername.getText();
                String password = tpassword.getText();
    
                connection conn = new connection();
    
                // ✅ Corrected query with PreparedStatement
                String query = "SELECT user_id FROM users WHERE username = ? AND password = ?";
                PreparedStatement stmt = conn.connection.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet resultset = stmt.executeQuery();
    
                if (resultset.next()) {
                    int userId = resultset.getInt("user_id"); // ✅ Get user ID
                    setVisible(false);
                    new Dashboard(userId); // ✅ Pass user ID to Dashboard
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == register) {
            new MyRegister();
        } else if (e.getSource() == back) {
            setVisible(false);
        }
    }
    

    public static void main(String[] args) {
        new MyLogin();
    }
}
