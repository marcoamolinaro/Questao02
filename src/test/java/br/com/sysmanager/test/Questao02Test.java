package br.com.sysmanager.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import org.junit.Test;

import br.com.sysmanager.Questao2;

public class Questao02Test {
	private Questao2 q2 = new Questao2();
	
	private void abrirArquivo(String title) {
	    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	    jfc.setDialogTitle(title);
	    int returnValue = jfc.showOpenDialog(null);
	    if (returnValue == JFileChooser.APPROVE_OPTION) {
			q2.setFile(jfc.getSelectedFile());
		}
	}
	
	@Test
	public void testAbrirArquivoInvalidoComMenos15Caracteres() {
		Boolean expect = true;
	
		this.abrirArquivo("TESTE DO ARQUIVO COM MENOS 15 CARACTERES");
		
		Boolean actual = (q2.getFile().length() < 15 ? true : false);
		
		assertEquals("Arquivo com < 15 caracteres", expect, actual);
	}

	@Test
	public void testAbrirArquivoInvalidoComMaisDe100000Caracteres() {
		Boolean expect = true;
				
	    this.abrirArquivo("TESTE DO ARQUIVO COM MAIS DE 100000 CARACTERES");
	    
		Boolean actual = (q2.getFile().length() > 100000 ? true : false);
		
		assertEquals("Arquivo com < 15 caracteres", expect, actual);	
	}

	@Test
	public void testAbrirArquivoValido() {
		Boolean expect = true;
		
	    this.abrirArquivo("TESTE DO ARQUIVO VÁLIDO");
		
	    long tamanho = q2.getFile().length();
	    
		Boolean actual = (tamanho > 15 && tamanho < 100000 ? true : false);
		
		assertEquals("Arquivo com < 15 caracteres", expect, actual);
	}

	@Test
	public void testProcessar_arquivo() {
		Boolean expect = true;
		Boolean actual = false;
		
	    this.abrirArquivo("TESTE PROCESSAR ARQUIVO VÁLIDO");
		
	    String linha;
		try {
			linha = q2.processar_arquivo(q2.getFile());
			actual = (linha != null ? true : false);
		} catch (Exception e) {
		}
		
		assertEquals("Arquivo com < 15 caracteres", expect, actual);
	}
	
	@Test
	public void testExtrairResultado() {
		List<String> linhas = new ArrayList<String>();
		List<String> resultadosEsperados = new ArrayList<String>();
		
		linhas.add("<h1>Nayeem loves counseling</h1>");
		linhas.add("<h1>");
		linhas.add("<h1>Sanjay has no watch</h1>");
		linhas.add("</h1>");
		linhas.add("<par>So wait for a while</par>");
		linhas.add("<Amee>safat codes like a ninja</amee>");
		linhas.add("<SA premium>Imtiaz has a secret crush</SA premium>");
		
		resultadosEsperados.add("Nayeem loves counseling");
		resultadosEsperados.add("Sanjay has no watch");
		resultadosEsperados.add("So wait for a while");
		resultadosEsperados.add("None");
		resultadosEsperados.add("Imtiaz has a secret crush");
		
		q2.extrairResultado(linhas);
		
		Boolean expect = true;
		Boolean actual = true;
		
		if (!q2.getResultados().isEmpty()) {
			for (String s : q2.getResultados()) {
				if (!resultadosEsperados.contains(s)) {
					actual = false;
				}
			}
		}
				
		assertEquals("Arquivo com < 15 caracteres", expect, actual);
	}


}
