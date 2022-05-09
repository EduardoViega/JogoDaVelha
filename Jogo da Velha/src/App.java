import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        
        Campo[][] velha = new Campo[3][3];
        char simboloAtual='X';
        Boolean game=true;
        char vitoria = ' ';
        Scanner scan=new Scanner(System.in);

        iniciaJogo(velha);

        while(game){
            desenhaJogo(velha);
            vitoria = verificaVitoria(velha);
            if(vitoria != ' '){
                System.out.printf("Jogador %c venceu%n", vitoria);
                break;
            }
            try{
                if(verificaJogada(velha, jogar(scan, simboloAtual), simboloAtual)){
                    if(simboloAtual == 'X'){
                        simboloAtual = 'O';
                    }else{
                        simboloAtual = 'X';    
                    }
                }
            }catch(Exception e){
                System.out.printf("Erro");
            }
        }   
        System.out.printf("Fim do jogo");
    }

    public static void desenhaJogo(Campo[][] velha){
        limparTela();
        System.out.println("   0   1   2");
        System.out.printf("0  %c | %c | %c %n",velha[0][0].getSimbolo(),velha[0][1].getSimbolo(),velha[0][2].getSimbolo());
        System.out.printf("   -----------");
        System.out.printf("%n1  %c | %c | %c %n",velha[1][0].getSimbolo(),velha[1][1].getSimbolo(),velha[1][2].getSimbolo());
        System.out.printf("   -----------");
        System.out.printf("%n2  %c | %c | %c %n%n",velha[2][0].getSimbolo(),velha[2][1].getSimbolo(),velha[2][2].getSimbolo());
    }

    public static void limparTela(){
        for(int cont=0; cont<200; cont++){
            System.out.println("");
        }
    }

    public static int[] jogar(Scanner scan, char sa){
        int p[] = new int[2];
        System.out.printf("%s %c%n","Quem Joga: ", sa);
        System.out.print("Informe a linha:");
        p[0]=scan.nextInt();
        System.out.print("Informe a coluna:");
        p[1]=scan.nextInt();
        return p;
    }

    public static Boolean verificaJogada(Campo[][] velha, int p[], char simboloAtual){
        if(velha[p[0]][p[1]].getSimbolo() == ' '){
            velha[p[0]][p[1]].setSimbolo(simboloAtual);
            return true;
        }
        return false;
    }

    public static void iniciaJogo(Campo[][] velha){
        for(int l=0; l<3; l++){
            for(int c=0; c<3; c++){
                velha[l][c] = new Campo();
            }
        }

    }

    public static char verificaVitoria(Campo[][] velha){
        //Linhas e colunas:
        for(int i=0; i<3; i++){
            if     (velha[i][0].getSimbolo() == velha[i][1].getSimbolo() && velha[i][0].getSimbolo() == velha[i][2].getSimbolo()){
                if(velha[i][0].getSimbolo() != ' '){
                    return velha[i][0].getSimbolo();
                }
            }
            else if(velha[0][i].getSimbolo() == velha[1][i].getSimbolo() && velha[0][i].getSimbolo() == velha[2][i].getSimbolo()){
                if(velha[0][i].getSimbolo() != ' '){
                    return velha[0][i].getSimbolo();
                }
            }
        }
        //Diagonais:
        if(velha[0][0].getSimbolo() == velha[1][1].getSimbolo() && velha[0][0].getSimbolo() == velha[2][2].getSimbolo()){
            if(velha[0][0].getSimbolo() != ' '){
                return velha[0][0].getSimbolo();
            }
        }else if(velha[0][2].getSimbolo() == velha[1][1].getSimbolo() && velha[0][2].getSimbolo() == velha[2][0].getSimbolo()){
            if(velha[0][2].getSimbolo() != ' '){
                return velha[0][2].getSimbolo();
            }
        }
        return ' ';
    }
}