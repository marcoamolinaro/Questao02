package br.com.sysmanager;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Questao2 {  

	private String texto;
	private File file;
	
	public Questao2() {}  
	
	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	public static void main(String[] args) {  
	    Questao2 q2 = new Questao2();
	    
	    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	    jfc.setDialogTitle("Escolha o arquivo");
	    int returnValue = jfc.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
			q2.setFile(jfc.getSelectedFile());
			System.out.println(q2.getFile().getAbsolutePath());
		}
	    
	    try {	 
	    	long tamanho = q2.getFile().length();
	    	
	    	if (q2.getFile().length() < 15) {
	    		JOptionPane.showMessageDialog(null, "Arquivo inválido, tem menos de 15 carateres.\nTamnho do arquivo lido é " + tamanho);
	    		return;
	    	}
	    	
	    	if (q2.getFile().length() > 100000) {
	    		JOptionPane.showMessageDialog(null, "Arquivo inválido, tem mais de 100.000 carateres.\nTamnho do arquivo lido é " + tamanho);
	    		return;	    		
	    	}
	    	
	    	String lines = q2.processar_arquivo(q2.getFile());
	    	
	    	System.out.println(q2.getFile().length());
	    } catch (Exception e){
	    	JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo");
	    	return;
	    }
	}  
	
	public String processar_arquivo(File f) throws Exception {
		String lines = "";
		
		Scanner sc = new Scanner(f);
		
	    while (sc.hasNextLine()) {
	    	lines += sc.nextLine() + "\n"; 
	    } 
		
	    System.out.println(lines);
	    
		return lines;
	}
} 