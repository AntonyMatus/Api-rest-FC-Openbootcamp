package com.example.Sprint5ApiRest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidatos")
public class Candidato implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String pais;
    private String ciudad;
    private String telefono;
    @Column(unique = true)
    private String email;
    private boolean traslado;

    @Enumerated(value = EnumType.STRING)
    private Precencialidad precencialidad;

    @JsonIgnore
    @ManyToOne(cascade ={CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "candidatos_etiquetas",
            joinColumns = @JoinColumn(name = "candidatos_id"),
            inverseJoinColumns = @JoinColumn(name = "etiqueta_id"))
    private Set<Etiqueta> etiquetas = new HashSet<>();

    public Candidato() {
    }

    public Candidato(Long id, String nombre, String pais, String ciudad, String telefono, String email, boolean traslado, Precencialidad precencialidad, Usuario usuario, Set<Etiqueta> etiquetas) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
        this.traslado = traslado;
        this.precencialidad = precencialidad;
        this.usuario = usuario;
        this.etiquetas = etiquetas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTraslado() {
        return traslado;
    }

    public void setTraslado(boolean traslado) {
        this.traslado = traslado;
    }

    public Precencialidad getPrecencialidad() {
        return precencialidad;
    }

    public void setPrecencialidad(Precencialidad precencialidad) {
        this.precencialidad = precencialidad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }


    @Override
    public String toString() {
        String trasLD = "";
        if (traslado) {
            trasLD = "SI";
        } else {
            trasLD = "NO";
        }
        return "Candidato{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", traslado=" + trasLD +
                ", precencialidad=" + precencialidad +
                ", usuario=" + usuario +
                ", etiquetas=" + etiquetas +
                '}';
    }

    public enum Precencialidad {
        PRESENCIAL, REMOTO, MIXTO;
    }

}
