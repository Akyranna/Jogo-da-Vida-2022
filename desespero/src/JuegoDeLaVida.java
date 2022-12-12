import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;
import java.util.Scanner;

public class JuegoDeLaVida {
    public static Random r = new Random();
    public static Scanner ler = new Scanner(System.in);
    public static int  resp, modificador, perg, contador, salario, inss, pos = 0;
    public static int [] repete = new int[30];
    public static String profissao, nome, animal, nomeanimal;
    public static boolean doente = false, twistedfate, gameover = false, pet = false;
    public static int saude = 100, constituicao = 10, inteligencia = r.nextInt(46) + 5, felicidade = r.nextInt(61) + 10, carisma = 0, money = 0;


    public static void main(String[] args) throws FileNotFoundException {
        menu();
        System.out.println("\n\n");
        System.out.println("""
                                  =-=     =-=     =-=    Você nasceu!!   =-=     =-=     =-=
                          Um belo espermatozóide que se desenvolveu em uma linda criança cheia de vida
                      e que desde cedo já toma decisões que podem afetar como sua história irá se desenrolar !
             """);
        for(int i = 0; i <= 6; i++){
            mostrastats();
            feto();
        }System.out.println("\n\n");
        System.out.println("""
                                 =-=   =-=   =-=  Você está ficando mais velho!!  =-=   =-=   =-=
                           
                   Suas escolhas são importantes, e eventos como ganhar ou perder dinheiro serão mais impactantes !
                  Agora além de escolhas afetarem seus status, seus status afetam os resultados das suas escolhas !
                                         ( O status requerido aparece entre parênteses )
             """);
        for(int i = 0; i <= 6; i++){
            mostrastats();
            muleq();
        }System.out.println("\n\n");
        System.out.println("""
                                  =-=   =-=   =-=  Você está ficando mais velho!!  =-=   =-=   =-=
                           
               Agora que terminou a faculdade, pode escolher um emprego de acordo com seus status para ganhar dinheiros !
              Você também passa a pagar as contas de casa sozinho, e pode ficar doente se não tiver uma boa constituição !
             """);
        emprego();
        //recebe salário a cada 2 turnos, desconta 1042 de imposto de renda a cada 4 turnos.
        for(int i = 0; i <= 7; i++){
            mostrastats();
            adulto();
            if(i % 2 == 0){
                System.out.println("Você recebeu " + salario + " dinheiros por trabalhar como " + profissao);
                money += salario;
                inss();
            }
            if(i % 4 == 0 && i != 0){
                int contas = r.nextInt(400) + 700;
                System.out.println("Você pagou " + contas + " em contas!");
                money -= contas;
            }
            doenca(3);
        }System.out.println("\n\n");
        System.out.println("""
                                 =-=     =-=     =-=     Você está ficando mais velho!!     =-=     =-=     =-=
                                
                                                      Você agora recebe aposentadoria !
                                           Caso tenha contribuído, também recebe um bônus do INSS !
                                       Por estar com mais idade, também está mais suscetível a doenças !
             """);
        for(int i = 0; i <= 7; i++){
            mostrastats();
            veio();
            if(i % 2 == 0){
                System.out.println("\nVocê recebeu " + (salario + inss) + " de aposentadoria!");
                money = money + salario + inss;
            }
            if(i % 4 == 0 && i != 0){
                int contas = r.nextInt(400) + 700;
                System.out.println(">> Você pagou " + contas + " em contas!");
                money -= contas;
            }
            doenca(4);
        }
        System.out.println("\n\n                    =-=     =-=     =-=     Toda jornada chega a um fim!!     =-=     =-=     =-=\n\n");
        System.out.println(nome + " faleceu com " + (r.nextInt(25) + 85) + " anos depois de viver uma vida movimentada e cheia de decisões difíceis\n" +
                "Acumulou um patrimônio de " + money + " dinheiros\nTrabalhou sendo " + profissao + " por " + (r.nextInt(30) + 20) + " anos!");
        if(pet){
            System.out.println("Teve um " + animal + " que se chamava " + nomeanimal);
        }
    }

    public static void feto() throws FileNotFoundException {
            pergunte(1);
            consq();
            derrota();
    }
    public static void muleq() throws FileNotFoundException {
            pergunte(2);
            consq();
            derrota();
    }
    public static void adulto() throws FileNotFoundException {
            pergunte(3);
            consq();
            derrota();
    }
    public static void veio() throws FileNotFoundException {
            pergunte(4);
            consq();
            derrota();
    }


    public static void pergunte(int idade) throws FileNotFoundException {
            Scanner escolhas = new Scanner(new FileReader("D://desespero//src//escolhas")).useDelimiter("\\n");
            ler(escolhas, idade);
    }
    public static void repetidor(int idade){
        //Serve pra não repetir as mesmas perguntas, usando um vetor que salva quais já foram e as confere depois.
        boolean repetiu = true;
        idade--;
        while(repetiu){
            int deuruim = 0;
            perg = r.nextInt(14);
            perg = perg + (idade * 14);
            for(int i = 0; i < 30; i++){
                if(perg == repete[i]){
                    deuruim++;
                }
            }if(deuruim == 0){
                repete[pos] = perg;
                pos++;
                repetiu = false;
            }
        }
    }
    public static void ler(Scanner perguntas, int idade){
        //escolhe a linha baseada na idade e em 1 random, vai até ela e lê a linha da pergunta e das respostas.
        //Perg * 6 é pra chegar na linha (pergunta e opcoes ocupam 6 linhas) , opcao lê as opções e conta quantas tem, e para quando não tem mais.
        contador = 0;
        boolean pare = false;
        repetidor(idade);
        for(int pl = perg * 6; pl > 0; pl--) {
            perguntas.next();
        }System.out.println("\n" + perguntas.next() + "\n");
        while(!pare){
            String opcao = perguntas.next();
            int a;
                a = (opcao.compareTo("a"));
                if(a < -6){
                    pare = true;
                }else {
                    System.out.println(opcao);
                    contador++;
                }
        }
    }
    public static void menu(){
        for(int i = 0; i < 30; i++){
            repete[i] = -1;
        }
        System.out.print("""
                      -=-=-     Life Simulator 2022     -=-=-
                            
                Digite o seu nome para começarmos o experimento social ->
                """);
        nome = ler.nextLine();
        System.out.println("\n\n -=- -=- -=-  Essa é a história e vida de " + nome + "  -=- -=- -=-");
        System.out.println(">>  Todos os acontecimentos, situações e escolhas são meramente fictícios e voltadas para nosso entretenimento.\n>>  Ficar ofendido ou lacrar em cima disso é coisa de otário\n\n>>  Tome decisões e viva a melhor ( ou pior ) vida possível!");
        System.out.println(">  Ao longo da sua história, acontecimentos ocorrerão e você precisará tomar algumas escolhas, o que moldará a sua pessoa");
        System.out.println(">  Algumas mecânicas surgem com o tempo, mas as escolhas são suas e não há de fato uma escolha 'errada' no jogo");
    }
    public static void consq() throws FileNotFoundException {
        //confere a escolha que você faz pra gente que tentar fazer gorilagem, condicionais extras pra não dar problema nas primeiras perguntas.
        //também lê qual stat é afetado, e escreve isso no próximo método (consqstat).
        boolean tacerto = false;
        while(!tacerto) {
            resp = ler.nextInt() - 1;
            if (resp < 0 || resp >= contador) {
                System.out.println("Opção inválida");
            }else {
                tacerto = true;
            }
        }
        int linha;
        linha = (perg * 5) + resp;
        Scanner consqboa = new Scanner(new FileReader("D://desespero//src//consequenciasucesso")).useDelimiter("\\n");
        Scanner consqruim = new Scanner(new FileReader("D://desespero//src//consequenciafracasso")).useDelimiter("\\n");
        Scanner sucesso = new Scanner(new FileReader("D://desespero//src//sucesso")).useDelimiter("\\s");
        Scanner fracasso = new Scanner(new FileReader("D://desespero//src//fracasso")).useDelimiter("\\s");
        conferidor(linha);
        if(twistedfate){
            mudar(linha, sucesso, consqboa);
        }else {
            System.out.println("Fracassou!");
            mudar(linha, fracasso, consqruim);
        }if(perg == 50){
            System.out.println("-=-  -=- Qual o nome quer dar pro seu animal de estimação? -=-  -=-");
            String [] tiposanimal = {"cachorro", "gato", "papagaio", "porquinho da Índia"};
            animal = tiposanimal[resp];
            pet = true;
            nomeanimal = ler.next();
        }
    }
    public static void conferidor(int linha) throws FileNotFoundException {
        //Usa os stats pra definir sucesso ou fracasso.
        //Twistedfate true é sucesso, false é fracasso.
        Scanner conferir = new Scanner(new FileReader("D://desespero//src//requerimento")).useDelimiter("\\s");
        for(int pula = 0; pula < linha; pula++){
            conferir.nextLine();
        }
        twistedfate = true;
        if(saude < conferir.nextInt()){
            twistedfate = false;
        }if(constituicao < conferir.nextInt()){
            twistedfate = false;
        }if(inteligencia < conferir.nextInt()){
            twistedfate = false;
        }if(felicidade < conferir.nextInt()){
            twistedfate = false;
        }if(carisma < conferir.nextInt()){
            twistedfate = false;
        }if(money < conferir.nextInt()){
            twistedfate = false;
        }
    }
    public static void mudar(int linha, Scanner status, Scanner mudar){
        //Muda stat com base no sucesso ou fracasso do método conferidor.
        for(int i = linha; i > 0; i--){
            status.nextLine();
            mudar.nextLine();
        }
        System.out.println(mudar.nextLine() + "\n");
        consqstat(status, "Saúde");
        saude += modificador;
        consqstat(status, "Constituição");
        constituicao += modificador;
        consqstat(status, "Inteligência");
        inteligencia += modificador;
        consqstat(status, "Felicidade");
        felicidade += modificador;
        consqstat(status, "Carisma");
        carisma += modificador;
        consqstat(status, "Money");
        money += modificador;
        limitastat();
    }
    public static void consqstat(Scanner num, String nomestat){
        //mostra qual stat foi afetado desse jeito:
        //cria um modificador que lê o proximo int no texto "efeito" e verifica se aumentou ou diminuiu.
        modificador = Integer.parseInt(num.next());
        if(modificador > 0){
            if(nomestat.equals("Money")){
                System.out.print("\nVocê ganhou " + modificador + " dinheiros !");
            }else {
                System.out.print(">> " + nomestat + " aumentou em " + modificador + "%   ");
            }
        }if(modificador < 0){
            if(nomestat.equals("Money")){
                System.out.print("\nVocê perdeu " + modificador + " dinheiros !");
            }else {
                System.out.print(">> " + nomestat + " diminuiu em " + modificador + "%   ");
            }
        }
    }
    public static void mostrastats() {
        //quem diria. mostra stats.
        System.out.println("\n                        -=- -=- -=- -=- -=- -=-   Status   -=- -=- -=- -=- -=- -=-");
        System.out.print("    Saúde     |");
        pauzin(saude);
        System.out.print(" Constituição |");
        pauzin(constituicao);
        System.out.print(" Inteligência |");
        pauzin(inteligencia);
        System.out.print("  Felicidade  |");
        pauzin(felicidade);
        System.out.print("    Carisma   |");
        pauzin(carisma);
        System.out.println("                     -=- -=- -=- -=- -=- -=-   Dinheiros: " + money + "   -=- -=- -=- -=- -=- -=-\n");
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            System.out.println("UUUUUUUUUUUUI");
        }
    }
    public static void pauzin(int stat){
        //pauzinho dos stats pra representar visualmente.
        for(int i = 0; i < stat; i++){
            System.out.print("=");
        }
        System.out.println("  " + stat + "%");
    }
    public static void limitastat(){
        //não deixa o stats passar de 100 ou ficar menor que 0 pra n ficar zoado.
        if(saude > 100){
            saude = 100;
        }if(constituicao > 100){
            constituicao = 100;
        }if(inteligencia > 100){
            inteligencia = 100;
        }if(felicidade > 100){
            felicidade = 100;
        }if(carisma > 100){
            carisma = 100;
        }
        if(constituicao < 0){
            constituicao = 0;
        }if(inteligencia < 0){
            inteligencia = 0;
        }if(felicidade < 0){
            felicidade = 0;
        }if(carisma < 0){
            carisma = 0;
        }
    }
    public static void emprego() throws FileNotFoundException {
        //escolha dos empregos
        //recebe salario e cada profissao tem stats requeridos
        //se não tem stats suficientes, aparece frases dizendo o que falta e pergunta denovo

        String [] emprego = {"Pedreiro","Médico","Farmacêutico","Professor de filosofia","Astrônomo","Policial",
                "Mecânico","Garçom","Empresário","Químico","Programador","Faxineiro","Jogador de futebol","Militar",
                "Motorista de aplicativo","Corretor de imóveis","Advogado","Contrabandista","Traficante","Limpa-fossa",
                "Streamer","Professor","Mendigo","Coach Motivacional","Motorista de ônibus","Vendedor","Pintor","Cantor","Rapper de anime",
                "Garoto de Programa","Maquiador","Bombeiro","Vidraceiro","Apresentador de TV","Pizzaiolo",
                "Arquiteto","Jornalista","Secretário","Guia turístico","Ator","Dançarino do Faustão","Instrutor de pilates","Encanador",
                "Eletricista","Caixa de mercado","Psicólogo","Carpinteiro","Filósofo","Entregador do IFood","Repórter","Harry Potter",
                "Hoteleiro","Chef","Piloto de avião","Agiota","Médium","Senhor de engenho","Pescador","Assassino de aluguel","Jogador de truco profissional",
                "Cigano","Reprodutor de elefantes","Estelionatário"};
        mostrarempregos(emprego);
        int capaz = 0;
        boolean errado = true;
        while(errado){
            capaz = ler.nextInt();
            if(capaz < 1 || capaz > 63) {
                System.out.println("Escolha uma opção válida!");
            }else{
                Scanner reqemprego = new Scanner(new FileReader("D://desespero//src//empregos")).useDelimiter("\\s");
                for(int i = capaz - 1; i > 0; i--) {
                    reqemprego.nextLine();
                }if(saude >= reqemprego.nextInt()){
                    if(constituicao >= reqemprego.nextInt()){
                        if(inteligencia >= reqemprego.nextInt()){
                            if(felicidade >= reqemprego.nextInt()){
                                if(carisma >= reqemprego.nextInt()) {
                                    errado = false;
                                    salario = reqemprego.nextInt();
                                    break;
                                }else{
                                    System.out.println("Você não é aceito nessa profissão  ( Carisma )");
                                }
                            }else{
                                System.out.println("Você não se sente bem com isso  ( Felicidade )");
                            }
                        }else{
                            System.out.println("Te falta conhecimento para isso  ( Inteligencia )");
                        }
                    }else{
                        System.out.println("Você não tem porte físico para isso  ( Constituição )");
                    }
                }else{
                    System.out.println("Você não está saudável o suficiente para isso  ( Saúde )");
                }
                System.out.println("Você não consegue ser " + emprego[capaz - 1].toLowerCase() + "! Seus stats são muito baixos\nTente outra profissão:");
            }
        }
        profissao = emprego[capaz - 1];
        System.out.println("Você se tornou " + profissao.toLowerCase() + "\nSeu salário é de " + salario + " dinheiros!");
    }
    public static void mostrarempregos(String [] emprego){
        System.out.println("Escolha o seu futuro!");
        //tabela dos empregos pra escolher
        for(int i = 0 ; i < 9; i++){
            System.out.print("[" + (i + 1) + "]" + " " + emprego[i]);
            for(int j = 25 - emprego[i].length(); j > 0; j--){
                System.out.print(" ");
            }System.out.print("[" + (i + 10) + "]" + " " + emprego[i + 9]);
            for(int k = 25 - emprego[i + 9].length(); k > 0; k--){
                System.out.print(" ");
            }System.out.print("[" + (i + 19) + "]" + " " + emprego[i + 18]);
            for(int k = 25 - emprego[i + 18].length(); k > 0; k--){
                System.out.print(" ");
            }System.out.print("[" + (i + 28) + "]" + " " + emprego[i + 27]);
            for(int k = 25 - emprego[i + 27].length(); k > 0; k--){
                System.out.print(" ");
            }System.out.print("[" + (i + 37) + "]" + " " + emprego[i + 36]);
            for(int k = 25 - emprego[i + 36].length(); k > 0; k--){
                System.out.print(" ");
            }System.out.print("[" + (i + 46) + "]" + " " + emprego[i + 45]);
            for(int k = 25 - emprego[i + 45].length(); k > 0; k--){
                System.out.print(" ");
            }System.out.print("[" + (i + 55) + "]" + " " + emprego[i + 54]);
            System.out.println(" ");
        }
    }
    public static void doenca(int idade){
        //cria doenças e rola um random baseado na idade.
        //se você NÃO ESTÁ DOENTE, verifica se o random é maior que sua constituição, e se for pega doença e toma dano.
        //se você ESTÁ DOENTE, roda outro random que, se for menor que sua constituição, você se livra da doença.
        int c1 = r.nextInt((idade * 15) + 20);
        //tem 25 doencas
        String [] doencas = {"pneumonia", "disfunção erétil", "calvicie", "bulimia", "COVID-23", "leucemia", "alzheimer",
                "febre amarela", "enxaqueca", "anemia", "AIDS", "câncer de mama", "depressão", "catapora", "caxumba", "dengue",
                "asma", "gastrite", "lepra", "poliomielite", "hepatite B", "hemorróida", "microcefalia" , "candidíase", "gripe"};

        if(doente){
            saude -= 5;
            constituicao -= 5;
            if(r.nextInt(50) < constituicao){
                System.out.println("\nVocê se sente menos doente. Você está curado!\nConstituição aumentada!");
                saude += 10;
                constituicao += 10;
                felicidade += 5;
                doente = false;
            }
        }
        if(c1 > constituicao && !doente){
            doente = true;
            felicidade -= 5;
            System.out.println("Você ficou doente! Você está com " + doencas[r.nextInt(24)]);
        }
    }
    public static void inss(){
        //Paga INSS pra receber depois na aposentadoria
        boolean errado = true;
        int resp = 0;
        System.out.println("""
                
                Você quer contribuir com o INSS (50 dinheiros) ?
                [1] Sim
                [2] Não""");
        while(errado) {
            resp = ler.nextInt();
            if(resp == 1 || resp == 2){
                errado = false;
            }else{
                System.out.println("Digite uma opção válida!");
            }
        }if(resp == 1){
            money -= 50;
            inss += 100;
        }
    }
    public static void derrota(){
        //confere tipos de derrota no jogo e mostra a causa dela e para o código
        if(saude <= 0){
            gameover = true;
            if(doente){
                System.out.println("\n Você ficou doente por muito tempo e acabou morrendo!");
            }else {
                System.out.println("\n Você se feriu muito e morreu de infarto do miocárdio!");
            }
        }
        if (money < -500) {
            gameover = true;
            System.out.println("\n Você está com o nome no SPC e no SERASA! Sua casa e seus órgãos foram confiscados!");
        }
        if(gameover){
            System.exit(0);
        }
    }
}

