package window1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Login extends JFrame {

	private String id;
	private String pw;
	private JPanel contentPane;
	private JTextField tf_id;
	private JPasswordField tf_pw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame.frame_login.setVisible(true);
//					DB.connectDB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */ 
	public Login() {
		setTitle("로그인");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("rsc\\logo_icon.png"));
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 30, 1303, 829);

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		try{
			JLabel lb_background = new JLabel(new ImageIcon(ImageIO.read(new File("rsc\\Login.jpg"))));
			lb_background.setBackground(Color.WHITE);
			lb_background.setBounds(0, 0, 1300, 800);
			contentPane.add(lb_background);
		}
		catch(IOException s){
			s.printStackTrace();
		}

		tf_id = new JTextField();
		tf_id.setForeground(Color.WHITE);
		tf_id.setBackground(new Color(107, 142, 35));
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tf_id.setBounds(270, 400, 200, 30);
		contentPane.add(tf_id);
		tf_id.setColumns(10);

		JButton btn_login = new JButton("");
		btn_login.setBorderPainted(false);
		btn_login.setIcon(new ImageIcon("rsc\\icon\\btn_login.jpg"));
		btn_login.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_login.setBackground(Color.WHITE);
		btn_login.setBounds(605, 323, 57, 46);
		contentPane.add(btn_login);

		JButton btn_join = new JButton("");
		btn_join.setBorderPainted(false);
		btn_join.setIcon(new ImageIcon("rsc\\icon\\btn_join.jpg"));
		btn_join.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btn_join.setBackground(Color.WHITE);
		btn_join.setBounds(551, 381, 66, 18);
		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Frame.frame_join.reset();
				Frame.frame_join.setVisible(true);
			}
		});
		contentPane.add(btn_join);

		tf_pw = new JPasswordField();
		tf_pw.setBackground(new Color(107, 142, 35));
		tf_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tf_pw.setForeground(Color.WHITE);
		tf_pw.setBounds(270, 435, 200, 30);
		contentPane.add(tf_pw);

		class Listener implements ActionListener, KeyListener{
			public void actionPerformed(ActionEvent arg0) {
				login();
			}

			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					login();
			}

			public void keyReleased(KeyEvent e){}

			public void keyTyped(KeyEvent e){}
		}

		Listener listener = new Listener();

		btn_login.addActionListener(listener);
		tf_id.addActionListener(listener);
		tf_pw.addActionListener(listener);
	}

	/**
	 * 유저 정보를 불러와서 이에 해당하는 계정이 있으면 로그인을 하는 메소드
	 */
	private void login(){
//		DB.getDBInfo(); // 유저 정보 갱신

		id = tf_id.getText().toString();
		pw = new String(tf_pw.getPassword());
		if(id.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_login, "아이디를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(pw.length() == 0){
			JOptionPane.showMessageDialog(Frame.frame_login, "비밀번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			return;
		}

/*		if(DB.isPWCorrect(id, pw, false)){
			DB.userIndex = DB.tempIndex;
			DB.homeIndex = DB.tempIndex;
			Frame.frame_home.setVisible(true); // 홈화면 띄움
			tf_id.setText("");
			tf_pw.setText("");
			Frame.frame_login.setVisible(false); // 로그인화면 끔
			return;
		}
*/
		JOptionPane.showMessageDialog(Frame.frame_login, "아이디 또는 비밀번호가 일치하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
	}
}