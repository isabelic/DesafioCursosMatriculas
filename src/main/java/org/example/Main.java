package org.example;

import java.util.List;
import java.util.Scanner;

import org.example.dao.AlunoDAO;
import org.example.dao.CursoDAO;
import org.example.dao.MatriculaDAO;
import org.example.model.Aluno;

public class Main {
    public static void main(String[] args) {
        Scanner iz = new Scanner(System.in);

        AlunoDAO daoA = new AlunoDAO();
        CursoDAO daoC = new CursoDAO();
        MatriculaDAO daoM = new MatriculaDAO();

        int op;

        do {
            System.out.println("\n////// MENU PRINCIPAL //////");
            System.out.println("1 - Ver Alunos");
            System.out.println("2 - Ver Cursos");
            System.out.println("3 - Ver Matrículas");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            op = iz.nextInt();
            iz.nextLine();

            switch (op) {
                case 1:
                    menuAlunos(iz, daoA);
                    break;
                case 2:
                    menuCursos(iz, daoC);
                    break;
                case 3:
                    menuMatriculas(iz, daoM);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema..");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente!");
            }
        } while (op != 0);

        iz.close();
    }

    public static void menuAlunos(Scanner iz, AlunoDAO daoA) {
        int opcao;
        do {
            System.out.println("\n--->  MENU ALUNO  <---");
            System.out.println("1 - Listar todos os alunos");
            System.out.println("2 - Cadastrar novo aluno");
            System.out.println("3 - Listar alunos não matriculados");
            System.out.println("4 - Remover aluno");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = iz.nextInt();
            iz.nextLine();

            switch (opcao) {
                case 1:
                    List<Aluno> alunos = daoA.listarTodos();
                    System.out.println("\n---  ALUNOS  ---");
                    alunos.forEach(System.out::println);
                    break;

                case 2:
                    System.out.print("Nome do aluno: ");
                    String nome = iz.nextLine();
                    System.out.print("Email do aluno: ");
                    String email = iz.nextLine();

                    daoA.adicionar(nome, email);
                    System.out.println("Novo aluno cadastrado com sucesso!");
                    break;

                case 3:
                    List<Aluno> semMatricula = daoA.listarNaoMatriculados();
                    System.out.println("\n---  ALUNOS NÃO MATRICULADOS  ---");
                    if (semMatricula.isEmpty()) {
                        System.out.println("Nenhum aluno encontrado.");
                    } else {
                        semMatricula.forEach(System.out::println);
                    }
                    break;

                case 4:
                    System.out.print("ID do aluno a excluir: ");
                    int idAluno = iz.nextInt();
                    iz.nextLine();
                    daoA.removerPorID(idAluno);

                    break;

                case 0:
                    System.out.println("Voltando ao menu principal...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void menuCursos(Scanner iz, CursoDAO daoC) {
        int opcao;
        do {
            System.out.println("\n--->  MENU CURSO  <---");
            System.out.println("1 - Cadastrar novo curso");
            System.out.println("2 - Listar todos os cursos com seus alunos");
            System.out.println("3 - Remover curso");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            opcao = iz.nextInt();
            iz.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do curso: ");
                    String nome = iz.nextLine();
                    System.out.print("Descrição do curso: ");
                    String desc = iz.nextLine();

                    daoC.adicionarCurso(nome, desc);

                    System.out.println("Curso cadastrado com sucesso!");
                    break;

                case 2:
                    List<String> cursosComAlunos = daoC.listarCursosComAlunos();
                    System.out.println("\n---  CURSOS COM ALUNOS  ---");

                    cursosComAlunos.forEach(System.out::println);

                    break;

                case 3:
                    System.out.print("ID do curso a excluir: ");
                    int idCurso = iz.nextInt();
                    iz.nextLine();
                    daoC.removerCursoID(idCurso);
                    System.out.println("Curso excluído com sucesso!");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente");
            }
        } while (opcao != 0);
    }

    public static void menuMatriculas(Scanner iz, MatriculaDAO daoM) {
        int opcao;
        do {
            System.out.println("\n--->  MENU MATRÍCULA  <---");
            System.out.println("1 - Matricular aluno em curso");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");
            opcao = iz.nextInt();
            iz.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("ID do aluno: ");
                    int idAluno = iz.nextInt();
                    iz.nextLine();
                    System.out.print("ID do curso: ");
                    int idCurso = iz.nextInt();
                    iz.nextLine();

                    daoM.matricular(idAluno, idCurso);
                    System.out.println("Aluno matriculado com sucesso!");
                    break;

                case 0:
                    System.out.println("Voltando ao menu principal..");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente");
            }
        } while (opcao != 0);
    }
}
