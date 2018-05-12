package vknyazev_B20_A03_MusicSystem;

import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.NumberFormat;

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
				menuFileWrite();
			}
		});
		mnFile.add(mntmWrite);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFileExit();
			}
		});
		mnFile.add(mntmExit);

		JMenu mnSearch = new JMenu("Search");
		menuBar.add(mnSearch);

		JMenuItem mntmBySongTitle = new JMenuItem("By song title");
		mntmBySongTitle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSearchTitle();
			}
		});
		mnSearch.add(mntmBySongTitle);

		JMenuItem mntmByYearranking = new JMenuItem("By year/ranking");
		mntmByYearranking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSearchYearRanking();
			}
		});
		mnSearch.add(mntmByYearranking);

		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);

		JMenuItem mntmListAllMusic = new JMenuItem("List All Music");
		mntmListAllMusic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuReportsAll();
			}
		});
		mnReports.add(mntmListAllMusic);

		JMenuItem mntmListByYear = new JMenuItem("List by year");
		mntmListByYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuReportsYear();
			}
		});
		mnReports.add(mntmListByYear);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		textArea = new JTextArea();
		
		textArea.setFont(new Font(Font.MONOSPACED, 0, 12));
		textArea.setEditable(false);
		JScrollPane sp = new JScrollPane(textArea);
		contentPane.add(sp, BorderLayout.CENTER);
		
		try {
			ms = new MusicSystem();
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "music.txt is missing", "Error", 0);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Could not read data file", "Error", 0);
			System.exit(1);
		}

		if (ms.getSongs().size() == 0) {
			JOptionPane.showMessageDialog(this, "Empty data file", "Error", 0);
			System.exit(2);
		}
	}

	private String formatSongAsTableItemString(Song song) {
		return String.format("%-10d%-10d%-30s%-30s%-10d \n", song.getYear(), song.getRanking(), song.getArtist(),
				song.getTitle(), song.getDownloadCount());

	}

	private String makeTableHeader(String title) {
		return String.format("%30s \n%30s \n\n%-10s%-10s%-30s%-30s%-10s \n%90s \n", title, title.replaceAll(".", "-"), "Year", "Ranking", "Artist", "Title",
				"# Downloads",  new String(new char[90]).replace('\0', '-'));
	}

	private String formatSongAsTableString(Song song) {
		return String.format("%20s\n%20s\n%-25s %-25s\n%-25s %-25s\n%-25s %-25s\n%-25s %-25s\n%-25s %-25s\n", "Song Details", "-----------", "Year:", song.getYear(), "Ranking:", song.getRanking(), "Artist:", song.getArtist(), "Song Title:", song.getTitle(), "# Downloads:", song.getDownloadCount());
	}

	private void menuReportsYear() {
		String yearString = JOptionPane.showInputDialog(this, "Enter year:");
		if (yearString == null)
			return;
			
		
		try {
			int year = Integer.parseInt(yearString);
			textArea.setText("");

			textArea.append(makeTableHeader("Top Songs of " + year));
			for (Song song : ms.getSongs()) {
				if (song.getYear() == year)
					textArea.append(formatSongAsTableItemString(song));
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Year must be an integer", "Error", 0);
		}
	}

	private void menuReportsAll() {
		textArea.setText("");

		textArea.append(makeTableHeader("Top Songs of the Year"));
		NumberFormat nf = NumberFormat.getIntegerInstance();
		int currentYear = ms.getSongs().get(0).getYear();
		int downloadCount = 0;
		for (Song song : ms.getSongs()) {
			if (song.getYear() != currentYear) {
				textArea.append(String.format("%85s\n", "-------"));
				textArea.append(String.format("%68s: %15s \n\n", "Download Totals", nf.format(downloadCount * 1000)));

				downloadCount = 0;
				currentYear = song.getYear();
			}
			textArea.append(formatSongAsTableItemString(song));
			downloadCount += song.getDownloadCount();
		}

		// print last year
		textArea.append(String.format("%85s\n", "-------"));
		textArea.append(String.format("%68s: %15s \n\n", "Download Totals", nf.format(downloadCount * 1000)));

		textArea.setCaretPosition(0);
	}

	private void menuSearchYearRanking() {
		JPanel yearRankingSearchPanel = new JPanel(new GridLayout(2, 2));
		JTextField fldYear = new JTextField();
		JTextField fldRanking = new JTextField();
		yearRankingSearchPanel.add(new JLabel("Year: "));
		yearRankingSearchPanel.add(fldYear);
		yearRankingSearchPanel.add(new JLabel("Ranking: "));
		yearRankingSearchPanel.add(fldRanking);
		int reply = JOptionPane.showConfirmDialog(this, yearRankingSearchPanel, "Search", JOptionPane.PLAIN_MESSAGE);
		if (reply == 0) {
			try {
				int year = Integer.parseInt(fldYear.getText());
				int ranking = Integer.parseInt(fldRanking.getText());
				Song foundSong = ms.findSongByYearAndRanking(year,
						ranking);
				if (foundSong != null) {
					textArea.setText(formatSongAsTableString(foundSong));
				} else {
					JOptionPane.showMessageDialog(this, "The year " + year + " and ranking " + ranking + " was not found.", "Error", 0);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Fields must be integers", "Error", 0);
			}

		}

		textArea.setCaretPosition(0);
	}

	private void menuSearchTitle() {
		String songTitle = JOptionPane.showInputDialog(this, "Enter song name:");

		// Kill if nothing entered
		if (songTitle == null)
			return;

		Song foundSong = ms.findSongByTitle(songTitle);
		if (foundSong == null) {
			JOptionPane.showMessageDialog(this, "The song " + songTitle + " was not found.", "Error", 0);
			return;
		}
		
		textArea.setText(formatSongAsTableString(foundSong));


		
	}

	private void menuFileExit() {
		System.exit(0);
	}

	private void menuFileWrite() {
		try {
			ms.writeSorted();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Failed to write to disk.", "Error", 0);

		}

	}
}
