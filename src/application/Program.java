package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.print("Diretorio pasta: ");

		File path = new File(sc.nextLine());

		// vou pegar todas as pastas que estão nesse caminho
		// vetor de pastas para listar apenas diretórios e não arquivos
		File[] pastas = path.listFiles(File::isDirectory);

		System.out.println("\nPASTAS:");

		for (File x : pastas) {
			System.out.println(x);
		}

		// Buscar apenas os arquivos
		File[] arquivos = path.listFiles(File::isFile);
		System.out.println("\nARQUIVOS:");

		for (File x : arquivos) {
			System.out.println(x);
		}

		// criar sub pasta dentro do diretorio
		Boolean success = new File(path + "\\Conteudos").mkdir();
		System.out.println("\nDIRETORIO CRIADO: " + success);

		System.out.println("\nIMPORTANDO ARQUIVO\n");

		importarArquivo(arquivos);

		System.out.println("LEITURA DO ARQUIVO IMPORTADO\n");

		lerArquivos();

		sc.close();
	}

	public static void importarArquivo(File[] arquivos) {
		// path para escrever no arquivo
		String pathArquivo = "C:\\Users\\eliseu\\Desktop\\out.txt";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dt = new Date();

		// novo objeto Writer com fileWriter com construtor path // true se quiser
		// reescrever
		try (BufferedWriter bf = new BufferedWriter(new FileWriter(pathArquivo))) {

			bf.write("Data da importação: " + sdf.format(dt));
			bf.newLine();
			bf.newLine();

			for (File x : arquivos) {
				bf.write("Arquivo: " + x.getName());
				bf.newLine();
				bf.write("Diretorio: " + x.getParent());
				bf.newLine();
				bf.write("Diretorio comp: " + x.getPath());
				bf.newLine();
				bf.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void lerArquivos() {
		String path = "C:\\Users\\eliseu\\Desktop\\out.txt";

		// leitura de arquivo

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line = br.readLine(); // lê se o arquivo for diferente de nulo

			// enquanto o arquivo for diferente de nulo
			while (line != null) {
				System.out.println(line);
				line = br.readLine();
			}
		} catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}

}
