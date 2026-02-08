package application;

import java.util.Scanner;

import dao.UsuarioDao;
import dao.UsuarioDaoJDBC;
import model.Usuario;
import service.UsuarioService;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		UsuarioDao dao = new UsuarioDaoJDBC();
		UsuarioService usuarioService = new UsuarioService(dao);

		int opcao;

		do {
			System.out.println("\n1 - Cadastrar usuário");
			System.out.println("2 - Listar usuários");
			System.out.println("3 - Atualizar usuário");
			System.out.println("4 - Deletar usuário");
			System.out.println("5 - Buscar usuário");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {

			case 1:
				System.out.print("Nome: ");
				String nome = sc.nextLine();

				System.out.print("Email: ");
				String email = sc.nextLine();

				System.out.print("Idade: ");
				int idade = sc.nextInt();
				sc.nextLine();

				Usuario novoUsuario = new Usuario(nome, email, idade);
				usuarioService.cadastrar(novoUsuario);

				System.out.println("Usuário cadastrado com sucesso!");
				System.out.println(novoUsuario);
				break;

			case 2:
				dao.listar().forEach(System.out::println);
				break;

			case 3:
				System.out.print("ID do usuário: ");
				int idAtualizar = sc.nextInt();
				sc.nextLine();

				System.out.print("Novo nome: ");
				String novoNome = sc.nextLine();

				System.out.print("Novo email: ");
				String novoEmail = sc.nextLine();

				System.out.print("Nova idade: ");
				int novaIdade = sc.nextInt();
				sc.nextLine();

				Usuario usuarioAtualizado = new Usuario(idAtualizar, novoNome, novoEmail, novaIdade);

				dao.atualizar(usuarioAtualizado);
				System.out.println("Usuário atualizado!");
				break;

			case 4:
				System.out.print("ID do usuário: ");
				int idExcluir = sc.nextInt();
				sc.nextLine();

				dao.deletar(idExcluir);
				System.out.println("Usuário deletado!");
				break;

			case 5:
				System.out.print("ID do usuário: ");
				int idBuscar = sc.nextInt();
				sc.nextLine();

				Usuario usuario = dao.buscarPorId(idBuscar);
				System.out.println(usuario != null ? usuario : "Usuário não encontrado");
				break;

			case 0:
				System.out.println("Encerrando...");
				System.out.println("Encerrado!");
				break;

			default:
				System.out.println("Opção inválida");
			}

		} while (opcao != 0);

		sc.close();
	}
}