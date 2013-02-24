package net.sourceforge.jetris.score;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class HiScoreTable {

	
	// Attributes
	private List<HiScore> table;
	
	// Constructors
	private HiScoreTable() {
		// empty
	}
	
	// Statics Methods
	public static HiScoreTable load(String filename) throws IOException, ClassNotFoundException {
        HiScoreTable hiScoreTable = null;
        FileInputStream fileInputStream = new FileInputStream(new File(filename));
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		try {
	        hiScoreTable = (HiScoreTable) objectInputStream.readObject();
		} finally {
			if (objectInputStream != null) {
				objectInputStream.close();
			}
		}
        return hiScoreTable;
	}
	
	// Methods
	public void write(String filename) throws IOException {
        FileOutputStream fileInputStream = new FileOutputStream(new File(filename));
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileInputStream);
        try {
			objectOutputStream.writeObject(this);
		} finally {
			if (objectOutputStream!=null) {
				objectOutputStream.close();
			}
		}
	}
	
	public List<HiScore> getTop(int topN) {
		return table.subList(0, topN);
	}
	
	public void add(HiScore newScore) {
		// TODO
		//------; // Add newScore sorted
	}
	
}
