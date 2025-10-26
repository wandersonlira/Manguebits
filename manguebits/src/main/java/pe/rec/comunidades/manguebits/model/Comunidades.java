package pe.rec.comunidades.manguebits.model;

import jakarta.persistence.*;
import pe.rec.comunidades.manguebits.enums.Categoria;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "comunidades")
public class Comunidades implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comunidade")
    private Long id;
    @Column(name = "conteudo", nullable = false, length = 150)
    private String nome;
    @Column(name = "descricao", nullable = false, length = 550)
    private String descricao;
    @Column(name = "administrador", nullable = false, length = 150)
    private String administrador;
    @Column(name = "categoria", nullable = false, length = 20)
    private Categoria categoria;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "comunidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<Posts> posts = new ArrayList<Posts>();


    public Comunidades() {}
    public Comunidades(Long id, String nome, String descricao, String administrador,
                       Categoria categoria, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.administrador = administrador;
        this.categoria = categoria;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getDescricao() {return descricao;}
    public void setDescricao(String descricao) {this.descricao = descricao;}

    public String getAdministrador() {return administrador;}
    public void setAdministrador(String administrador) {this.administrador = administrador;}

    public Categoria getCategoria() {return categoria;}
    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    public LocalDateTime getCreatedAt() {return createdAt;}

    public LocalDateTime getUpdatedAt() {return updatedAt;}

    public List<Posts> getPosts() { return posts; }

    public void addPost(Posts post) {
        this.posts.add(post);
        post.setComunidade(this);
    }
    public void removePost(Posts post) {
        this.posts.remove(post);
        post.setComunidade(null);
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Comunidades that = (Comunidades) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
