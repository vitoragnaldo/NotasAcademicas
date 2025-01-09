import java.util.ArrayList;
import java.util.Scanner;

class Aluno {
    String nome;
    private final ArrayList<Double> notas;

    // Construtor
    public Aluno(String nome) {
        this.nome = nome;
        this.notas = new ArrayList<>();
    }

    // Adiciona uma nota ao aluno
    public void adicionarNota(double nota) {
        if (nota >= 0 && nota <= 10) {
            this.notas.add(nota);
        } else {
            System.out.println("Nota inválida! A nota deve ser entre 0 e 10.");
        }
    }

    // Calcula a média das notas
    public double calcularMedia() {
        double soma = 0;
        for (double nota : notas) {
            soma += nota;
        }
        return soma / notas.size();
    }

    // Verifica se o aluno foi aprovado ou reprovado
    public String verificarAprovacao() {
        double media = calcularMedia();
        if (media >= 7) {
            return "Aprovado";
        } else if (media >= 4) {
            return "Recuperação";
        } else {
            return "Reprovado";
        }
    }

    // Exibe as informações do aluno
    public void exibirInformacoes() {
        System.out.println("Nome: " + nome);
        System.out.print("Notas: ");
        for (double nota : notas) {
            System.out.print(nota + " ");
        }
        System.out.println();
        System.out.println("Média: " + calcularMedia());
        System.out.println("Situação: " + verificarAprovacao());
    }
}

class SistemaNotasAcademicas {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Aluno> alunos = new ArrayList<>();

        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n=== Sistema de Notas Acadêmicas ===");
            System.out.println("1. Cadastrar Aluno");
            System.out.println("2. Inserir Notas");
            System.out.println("3. Exibir Notas e Situação");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a linha em branco após o próximo int

            switch (opcao) {
                case 1:
                    // Cadastrar aluno
                    System.out.print("Digite o nome do aluno: ");
                    String nome = scanner.nextLine();
                    alunos.add(new Aluno(nome));
                    System.out.println("Aluno cadastrado com sucesso!");
                    break;

                case 2:
                    // Inserir notas
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        System.out.print("Digite o nome do aluno para inserir notas: ");
                        String nomeAluno = scanner.nextLine();
                        Aluno aluno = encontrarAluno(alunos, nomeAluno);

                        if (aluno != null) {
                            System.out.println("Digite as notas do aluno (Digite -1 para terminar):");
                            while (true) {
                                System.out.print("Nota: ");
                                double nota = scanner.nextDouble();
                                if (nota == -1) break;
                                aluno.adicionarNota(nota);
                            }
                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                    }
                    break;

                case 3:
                    // Exibir notas e situação do aluno
                    if (alunos.isEmpty()) {
                        System.out.println("Nenhum aluno cadastrado.");
                    } else {
                        System.out.print("Digite o nome do aluno para exibir as informações: ");
                        String nomeAlunoInfo = scanner.nextLine();
                        Aluno aluno = encontrarAluno(alunos, nomeAlunoInfo);

                        if (aluno != null) {
                            aluno.exibirInformacoes();
                        } else {
                            System.out.println("Aluno não encontrado.");
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    // Método auxiliar para encontrar aluno por nome
    private static Aluno encontrarAluno(ArrayList<Aluno> alunos, String nome) {
        for (Aluno aluno : alunos) {
            if (aluno.nome.equalsIgnoreCase(nome)) {
                return aluno;
            }
        }
        return null;
    }
}
