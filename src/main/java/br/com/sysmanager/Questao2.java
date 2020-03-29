package br.com.sysmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

public class Questao2 {  

	private Map<String, String> mapTagLinhas = new HashMap<String, String>();
	private List<String> resultados = new ArrayList<String>();
	private File file;
	
	public Questao2() {}  
	
	public Map<String, String> getMapTagLinhas() {
		return mapTagLinhas;
	}

	public void setMapTagLinhas(Map<String, String> mapTagLinhas) {
		this.mapTagLinhas = mapTagLinhas;
	}

	public List<String> getResultados() {
		return resultados;
	}

	public void setResultados(List<String> resultados) {
		this.resultados = resultados;
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
	    	
	    	String linha = q2.processar_arquivo(q2.getFile());
	    			    
		    List<String> linhas = new ArrayList<String>();
		    
		    linhas.addAll(Arrays.asList(linha.split("\n")));
		        	    	    	
	    	q2.extrai_resultado(linhas);
	    	
	    	System.out.println("Resultado:");
	    	
	    	for (String r : q2.resultados) {
	    		System.out.println(r);
	    	}
	    } catch (Exception e){
	    	System.out.println(e.getMessage());
	    	JOptionPane.showMessageDialog(null, "Erro ao abrir arquivo");
	    	return;
	    }
	}
	
	public void extrai_resultado(List<String> linhas) {
		int posInicioTagAbertura = 0;
		int posFinalTagAbertura = 0;
		String tagAbertura = "";
		int posInicioTagEncerramento = 0;
		int posFinalTagEncerramento = 0;
		String tagEncerramento = "";
		String resultado = "";
		
		for (String s : linhas) {
	    	posInicioTagAbertura = s.indexOf("<");		    
		    posFinalTagAbertura = s.indexOf(">");		    
		    tagAbertura = s.substring(posInicioTagAbertura +1 , posFinalTagAbertura);
		    posInicioTagEncerramento = s.indexOf("</");
		    
		    if (posInicioTagEncerramento < 0) continue;
		    
		    posFinalTagEncerramento = s.indexOf("</"+tagAbertura+">") + tagAbertura.length() + 2;
		    if (posInicioTagEncerramento > posFinalTagEncerramento) {
		    	this.resultados.add("None");
		    	continue;
		    }
		    tagEncerramento = s.substring(posInicioTagEncerramento +2 , posFinalTagEncerramento);
		    
		    if (tagAbertura.equals(tagEncerramento)) {
		    	resultado = s.substring(posFinalTagAbertura+1, posInicioTagEncerramento);
		    	this.resultados.add(resultado);
		    }
		}
	}

	public String processar_arquivo(File f) throws Exception {
		String linha = "";
		
		Scanner sc = new Scanner(f);
		
	    while (sc.hasNextLine()) {
	    	linha += sc.nextLine();
	    } 
	    
	    sc.close();
	    
	    linha = linha.replace("><", ">\n<");
	    
		return linha;
	}
} 