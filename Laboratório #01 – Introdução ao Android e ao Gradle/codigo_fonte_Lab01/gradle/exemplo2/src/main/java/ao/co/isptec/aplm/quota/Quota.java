package ao.co.isptec.aplm.quota;

/** Classe de dominio: uma citacao famosa (id, autor e texto). */
public class Quota {
    private Long id;
    private String autor;
    private String texto;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    @Override
    public String toString() {
        return "Quota{id=" + id + ", autor='" + autor + "', texto='" + texto + "'}";
    }

    public static void main(String[] args) {
        System.out.println("Aplicacao Quota em execucao");
        Quota q = new Quota();
        q.setId(Long.valueOf(1));
        q.setAutor("Joao Costa");
        q.setTexto("Deus e vida.");
        System.out.println(q);
    }
}
