/*
	Creator : Faddi Susanto
	Java database text to store your data
	This API created where SQL is not needed
	or created Application without using SQL database
*/

import java.io.*;
import java.util.*;

public class lullaby {

	private Secret secret;
	private String[][] store;
	private FileInputStream finput = null;
	private FileOutputStream foutput = null;
	private String slt = "";
	public String fileName = "";
	public int kolom;
	private String specialSplit = "@&#";

	/*
		fileName : file name for tables database
		kolom : number of array column
	*/
	public lullaby(String fileName, int kolom) {
		this.secret = new Secret();
		this.fileName = fileName;
		this.kolom = kolom;
		int fdata;
		try{
			this.finput = new FileInputStream(fileName); //nama files
		}catch(FileNotFoundException fnfe){
			System.out.println("File Tidak Ditemukan.");
		}
		try{
			while((fdata = this.finput.read()) != -1){
				this.slt += (char) fdata;
			}
			String[] ary = this.secret.Decryption(this.slt).split("\n");
			int lgt = ary.length;
			this.store = new String[lgt][kolom]; //kolom banyaknya kolom database ditentukan
			for(int i=0;i<this.store.length;i++){
				this.store[i] = ary[i].split(this.specialSplit);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
			this.finput.close();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
	}

	public void selectAll() throws Exception {
		String decrypted = this.secret.Decryption(this.slt);
		System.out.println(decrypted);
		return;
	}

	public String[][] selectMultiAll() {
		return this.store;
	}

	public String[] selectWhere(String equal, int where){
		String[] selects = null;
		for(int i=0;i<this.store.length;i++){
			if(this.store[i][where].equals(equal)){
				String ary = Arrays.toString(this.store[i]).replace("[","").replace("]","");
				selects = ary.split(",");
			}
		}
		return selects;
	}

	public String[][] selectMultiWhere(String equal, int where){
		String[][] selects = new String[this.store.length][this.kolom];
		for(int i=0;i<this.store.length;i++){
			if(this.store[i][where].equals(equal)){
				String ary = Arrays.toString(this.store[i]).replace("[","").replace("]","");
				selects[i] = ary.split(",");
			}
		}
		return selects;
	}

	public void insert(String[] inserts) throws Exception {
		String sva = "";
		for(int i=0;i<inserts.length;i++){
			sva += inserts[i] + this.specialSplit;
		}
		sva += "\n";
		String sve = this.secret.Encryption(sva);
		try{
			this.foutput = new FileOutputStream(this.fileName, true); //nama files
		}catch(FileNotFoundException fnfe){
			System.out.println("File Tidak Ditemukan.");
		}
		try{
			for(int i=0;i<sve.length();i++){
				this.foutput.write((int) sve.charAt(i));
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		try{
			this.foutput.flush();
			this.foutput.close();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		return;
	}

	public void update(String equal, int where, int oldString, String newString){
		String datas = "";
		for(int i=0;i<this.store.length;i++){
			if(this.store[i][where].equals(equal)){
				this.store[i][oldString] = newString;
				String st = Arrays.toString(this.store[i]).replace("[","").replace("]","").replace(", ",this.specialSplit).replace(",  ",this.specialSplit);
				this.store[i] = st.split(",");
			}
			datas += Arrays.toString(this.store[i]).replace("[","").replace("]","").replace(", ",this.specialSplit).replace(",  ",this.specialSplit) + "\n";
		}
		try{
			this.foutput = new FileOutputStream(this.fileName);
		}catch(FileNotFoundException fnfe){
			System.out.println("File Tidak Ditemukan");
		}
		try{
			String dataSave = this.secret.Encryption(datas);
			for(int i=0;i<dataSave.length();i++){
				this.foutput.write((int) dataSave.charAt(i));
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		try{
			this.foutput.flush();
			this.foutput.close();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		return;
	}

	public void delete(String equal, int where){
		String datas = "";
		for(int i=0;i<this.store.length;i++){
			if(this.store[i][where].equals(equal)){
				this.store[i] = null;
			}
			datas += Arrays.toString(this.store[i]).replace("[","").replace("]","").replace(", ",this.specialSplit).replace(",  ",this.specialSplit) + "\n";
		}
		try{
			this.foutput = new FileOutputStream(this.fileName);
		}catch(FileNotFoundException fnfe){
			System.out.println("File Tidak Ditemukan");
		}
		try{
			String dataSave = this.secret.Encryption(datas);
			for(int i=0;i<dataSave.length();i++){
				this.foutput.write((int) dataSave.charAt(i));
			}
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		try{
			this.foutput.flush();
			this.foutput.close();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		return;
	}
}
