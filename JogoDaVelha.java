import java.util.Scanner;

public class JogoDaVelha {

    static char[][] tabuleiro = new char[3][3];

    // Preenche o tabuleiro com espaços vazios
    static void iniciarTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabuleiro[i][j] = ' ';
            }
        }
    }

    // Mostra o tabuleiro na tela
    static void mostrarTabuleiro() {
        System.out.println();
        System.out.println("     1   2   3");
        for (int i = 0; i < 3; i++) {
            System.out.println("   +---+---+---+");
            System.out.print(" " + (i + 1) + " |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + tabuleiro[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println("   +---+---+---+");
        System.out.println();
    }

    // Verifica se a posição existe e está livre
    static boolean jogadaValida(int linha, int coluna) {
        if (linha < 0 || linha > 2 || coluna < 0 || coluna > 2) {
            return false;
        }
        return tabuleiro[linha][coluna] == ' ';
    }

    // Verifica se o jogador venceu (linhas, colunas e diagonais)
    static boolean venceu(char jogador) {
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }
        return false;
    }

    // Empate: nenhuma casa vazia
    static boolean empate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Lê um número inteiro com segurança
    static int lerNumero(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print("Digite apenas números: ");
        }
        return sc.nextInt();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String resposta;

        do {
            iniciarTabuleiro();
            char jogadorAtual = 'X';
            boolean fimDeJogo = false;

            System.out.println("=== JOGO DA VELHA ===");

            while (!fimDeJogo) {
                mostrarTabuleiro();
                System.out.println("Vez do jogador " + jogadorAtual);

                int linha, coluna;
                do {
                    System.out.print("Linha (1-3): ");
                    linha = lerNumero(sc) - 1;
                    System.out.print("Coluna (1-3): ");
                    coluna = lerNumero(sc) - 1;

                    if (!jogadaValida(linha, coluna)) {
                        System.out.println("Jogada inválida! Tente de novo.");
                    }
                } while (!jogadaValida(linha, coluna));

                tabuleiro[linha][coluna] = jogadorAtual;

                if (venceu(jogadorAtual)) {
                    mostrarTabuleiro();
                    System.out.println("Jogador " + jogadorAtual + " venceu!");
                    fimDeJogo = true;
                } else if (empate()) {
                    mostrarTabuleiro();
                    System.out.println("Deu velha! Empate.");
                    fimDeJogo = true;
                } else {
                    jogadorAtual = (jogadorAtual == 'X') ? 'O' : 'X';
                }
            }

            System.out.print("Jogar novamente? (s/n): ");
            resposta = sc.next();
        } while (resposta.equalsIgnoreCase("s"));

        System.out.println("Até a próxima!");
        sc.close();
    }
}