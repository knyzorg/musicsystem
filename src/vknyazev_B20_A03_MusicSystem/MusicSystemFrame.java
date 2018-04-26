package vknyazev_B20_A03_MusicSystem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MusicSystemFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private MusicSystem ms;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MusicSystemFrame frame = new MusicSystemFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MusicSystemFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 383);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmWrite = new JMenuItem("Write");
		mntmWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				menu_file_write();
			}
		});
		mnFile.add(mntmWrite);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_file_exit();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);
		
		JMenuItem mntmBySongTitle = new JMenuItem("By song title");
		mntmBySongTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_search_title();
			}
		});
		mnSearch.add(mntmBySongTitle);
		
		JMenuItem mntmByYearranking = new JMenuItem("By year/ranking");
		mntmByYearranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_search_year();
			}
		});
		mnSearch.add(mntmByYearranking);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmListAllMusic = new JMenuItem("List All Music");
		mntmListAllMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_reports_all();
			}
		});
		mnReports.add(mntmListAllMusic);
		
		JMenuItem mntmListByYear = new JMenuItem("List by year");
		mntmListByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu_reports_year();
			}
		});
		mnReports.add(mntmListByYear);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		contentPane.add(textArea, BorderLayout.CENTER);
	}
	
	private void menu_reports_year() {
		
	}
	
	private void menu_reports_all() {
		
	}
	
	private void menu_search_year(){
		
	}
	
	private void menu_search_title() {
		String songTitle = JOptionPane.showInputDialog(this, "Enter song name:");
		
		// Kill if nothing entered
		if (songTitle == null || songTitle == "")
			return;

		// TODO: Add search
	}
	
	private void menu_file_exit() {
		System.exit(0);
	}
	
	private void menu_file_write() {
		
	}

}
