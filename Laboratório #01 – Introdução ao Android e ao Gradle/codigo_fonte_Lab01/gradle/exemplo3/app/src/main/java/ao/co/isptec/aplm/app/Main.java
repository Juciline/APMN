package ao.co.isptec.aplm.app;

import ao.co.isptec.aplm.modelo.Quota;
import ao.co.isptec.aplm.servico.ServicoQuota;

public class Main {
    public static void main(String[] args) {
        ServicoQuota s = new ServicoQuota();
        s.adicionar(new Quota(1, "Joao Costa", "Deus e vida."));
        s.listar().forEach(System.out::println);
    }
}
