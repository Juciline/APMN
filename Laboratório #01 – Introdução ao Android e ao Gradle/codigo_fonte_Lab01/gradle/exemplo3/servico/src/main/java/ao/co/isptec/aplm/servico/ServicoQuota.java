package ao.co.isptec.aplm.servico;

import ao.co.isptec.aplm.modelo.Quota;
import java.util.ArrayList;
import java.util.List;

public class ServicoQuota {
    private final List<Quota> quotas = new ArrayList<>();

    public void adicionar(Quota q) { quotas.add(q); }
    public List<Quota> listar() { return List.copyOf(quotas); }
}
