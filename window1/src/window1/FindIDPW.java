package window1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author 김찬중 아이디 또는 비밀번호를 찾는 Class
 */
@SuppressWarnings("serial")
public class FindIDPW extends JFrame {
	
	DB db = new DB();

	private JPanel contentPane;
	private JTextField tf_findid_email;
	private JTextField tf_findpw_email;
	private JTextField tf_findid_name;
	private JTextField tf_findpw_id;

	public FindIDPW() {
		
		db.getMember();
		
		setResizable(false);
		setTitle("아이디/비밀번호 찾기");
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\rsc\\logo_icon.png"));
		setBounds(100, 100, 403, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_findid_email = new JTextField();
		tf_findid_email.setOpaque(false);
		tf_findid_email.setForeground(Color.BLACK);
		tf_findid_email.setFont(new Font("굴림", Font.PLAIN, 18));
		tf_findid_email.setBounds(135, 90, 215, 24);
		contentPane.add(tf_findid_email);
		tf_findid_email.setColumns(10);

		tf_findid_name = new JTextField();
		tf_findid_name.setOpaque(false);
		tf_findid_name.setForeground(Color.BLACK);
		tf_findid_name.setFont(new Font("굴림", Font.PLAIN, 18));
		tf_findid_name.setBounds(135, 131, 146, 24);
		contentPane.add(tf_findid_name);
		tf_findid_name.setColumns(10);

		tf_findpw_id = new JTextField();
		tf_findpw_id.setOpaque(false);
		tf_findpw_id.setForeground(Color.BLACK);
		tf_findpw_id.setFont(new Font("굴림", Font.PLAIN, 18));
		tf_findpw_id.setBounds(135, 330, 144, 24);
		contentPane.add(tf_findpw_id);
		tf_findpw_id.setColumns(10);

		tf_findpw_email = new JTextField();
		tf_findpw_email.setOpaque(false);
		tf_findpw_email.setForeground(Color.BLACK);
		tf_findpw_email.setFont(new Font("굴림", Font.PLAIN, 18));
		tf_findpw_email.setColumns(15);
		tf_findpw_email.setBounds(135, 372, 215, 24);
		contentPane.add(tf_findpw_email);

		JButton btn_findID = new JButton("");
		btn_findID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = tf_findid_email.getText();
				String name = tf_findid_name.getText();
				findID(email, name);
			}
		});
		btn_findID.setIcon(new ImageIcon(".\\rsc\\icon\\btn_find.PNG"));
		btn_findID.setBorderPainted(false);
		btn_findID.setBounds(127, 196, 165, 35);
		contentPane.add(btn_findID);

		JButton btn_findPW = new JButton("");
		btn_findPW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = tf_findpw_id.getText();
				String email = tf_findpw_email.getText();
				findPW(id, email);
			}
		});
		btn_findPW.setIcon(new ImageIcon(".\\rsc\\icon\\btn_find.PNG"));
		btn_findPW.setBorderPainted(false);
		btn_findPW.setBounds(127, 450, 165, 35);
		contentPane.add(btn_findPW);
		
		JLabel lb_background = new JLabel(new ImageIcon(".\\rsc\\Find.png"));
		lb_background.setBounds(0, 0, 400, 526);
		contentPane.add(lb_background);
		setContentPane(contentPane);
	}

	/**
	 * 이메일과 이름을 입력받아서 이에 일치하는 아이디를 메시지로 띄워주는 메소드
	 */
	
	private void findID(String email, String name) {
		int i;
		for (i = 0; i < Data.member_vector.size(); i++) {
			if (Data.member_vector.get(i).email.equals(email) && Data.member_vector.get(i).name.equals(name)) {
				JOptionPane.showMessageDialog(Frame.frame_join, "아이디는 " + Data.member_vector.get(i).id + "입니다.",
						"아이디 찾기", JOptionPane.INFORMATION_MESSAGE);
				break;
			}
		}
		if (i == Data.member_vector.size()) {
			JOptionPane.showMessageDialog(Frame.frame_join, "일치하는 계정이 존재하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 아이디, 이메일, 이름을 받아서 이에 일치하는 본인확인 질문을 띄워주는 메소드
	 */
	private void findPW(String id, String email) {
		int i;
		for (i = 0; i < Data.member_vector.size(); i++) {
			if (Data.member_vector.get(i).id.equals(id) && Data.member_vector.get(i).email.equals(email)) {
				String pwAnswer = "";
				switch (Data.member_vector.get(i).pwQuestion) {
				case 0:
					pwAnswer = "나의 장래희망은?";
					break;
				case 1:
					pwAnswer = "나의 보물 제 1호는?";
					break;
				case 2:
					pwAnswer = "가장 기억에 남는 선생님 성함은?";
					break;
				case 3:
					pwAnswer = "가장 기억에 남는 장소는?";
					break;
				case 4:
					pwAnswer = "나의 어머니의 성함은?";
					break;
				case 5:
					pwAnswer = "나의 아버지의 성함은?";
					break;
				}
				Data.userIndex = i;
				Frame.frame_findPW.setVisible(true);
				Frame.frame_findPW.lb_pwQuestion.setText(pwAnswer);
				Frame.frame_findIDPW.setVisible(false);
				break;
			}
		}
		if (i == Data.member_vector.size()) {
			JOptionPane.showMessageDialog(Frame.frame_join, "일치하는 계정이 존재하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
		}
	}

	void reset() {
		tf_findid_email.setText("");
		tf_findid_name.setText("");
		tf_findpw_id.setText("");
		tf_findpw_email.setText("");

	}
}
