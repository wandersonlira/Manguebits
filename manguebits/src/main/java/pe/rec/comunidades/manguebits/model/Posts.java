package pe.rec.comunidades.manguebits.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Posts {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long idPost;
    @Column(name = "conteudo", nullable = false, length = 380) private String conteudo;
    @Column(name = "curtidas", nullable = false) private Integer curtidas;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false) private LocalDateTime updatedAt;
    @JsonIgnore @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "id_comunidade", nullable = false) private Comunidades comunidade;


    public Posts() {}
    public Posts(Long idPost, String conteudo, Integer curtidas, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idPost = idPost;
        this.conteudo = conteudo;
        this.curtidas = curtidas;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getIdPost() { return idPost; }
    public String getConteudo() { return this.conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public Integer getCurtidas() { return curtidas; }
    public void setCurtidas(Integer curtidas) { this.curtidas = curtidas; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Comunidades getComunidade() { return comunidade; }
    public void setComunidade(Comunidades comunidade) { this.comunidade = comunidade; }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}