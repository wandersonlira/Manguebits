package pe.rec.comunidades.manguebits.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    private String nome;
    private Integer curtidas = 0;

    @Column(nullable = false)
    private Integer idComunidade;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getIdPost() { return idPost; }
    public void setIdPost(Long idPost) { this.idPost = idPost; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Integer getCurtidas() { return curtidas; }
    public void setCurtidas(Integer curtidas) { this.curtidas = curtidas; }
    public Integer getIdComunidade() { return idComunidade; }
    public void setIdComunidade(Integer idComunidade) { this.idComunidade = idComunidade; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}