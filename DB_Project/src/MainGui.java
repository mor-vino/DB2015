
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.sql.Connection;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class MainGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DMLHandler dmlHandler;
	private DDLHandler ddlHandler;
	private JTextField dmlQuery;
	private JTextField ddlQuery;
	private Connection connection;

	/**
	 * Launch the application.
	 */
	public void runGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MainGui(Connection c) {
		initialize();
        this.connection = c;
		this.dmlHandler = new DMLHandler(this.connection);
		this.ddlHandler = new DDLHandler(this.connection);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("data base project");
		setBounds(100, 100, 450, 300);
        JTabbedPane jtp = new JTabbedPane();
        getContentPane().add(jtp);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel();
        jtp.addTab("DML", jp1);
        jp1.setLayout(null);
        
        dmlQuery = new JTextField();
        dmlQuery.setHorizontalAlignment(SwingConstants.LEFT);
        dmlQuery.setFont(new Font("Lupa Planta", Font.BOLD, 17));
        dmlQuery.setText("enter your query here");
        dmlQuery.setBounds(26, 47, 381, 80);
        jp1.add(dmlQuery);
        dmlQuery.setColumns(10);
        
        JButton DMLSendBtn = new JButton("SEND");
        DMLSendBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Handler h = getDMLHandler();
        		// save the query as a string
        		String query = dmlQuery.getText();
        		h.handle(query);
        		
        	}
        });
        DMLSendBtn.setFont(new Font("Lupa Planta", Font.PLAIN, 16));
        DMLSendBtn.setBounds(168, 162, 89, 23);
        jp1.add(DMLSendBtn);
        jtp.addTab("DDL", jp2);
        jp2.setLayout(null);
        
        ddlQuery = new JTextField();
        ddlQuery.setText("enter your query here");
        ddlQuery.setHorizontalAlignment(SwingConstants.LEFT);
        ddlQuery.setFont(new Font("Lupa Planta", Font.BOLD, 17));
        ddlQuery.setColumns(10);
        ddlQuery.setBounds(27, 48, 381, 80);
        jp2.add(ddlQuery);
        
        JButton DDLDendBtn = new JButton("SEND");
        DDLDendBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		Handler h = getDDLHandler();
        		String query = ddlQuery.getText();
        		h.handle(query);
        	}
        });
        DDLDendBtn.setFont(new Font("Lupa Planta", Font.PLAIN, 16));
        DDLDendBtn.setBounds(158, 158, 89, 23);
        jp2.add(DDLDendBtn);
	}
	public Handler getDMLHandler() {
		return this.dmlHandler;
	}
	public Handler getDDLHandler() {
		return this.ddlHandler;
	}
}
