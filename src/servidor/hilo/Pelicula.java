package servidor.hilo;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private String id;
    private String titulo;
    private String director;
    private String precio;
    private List<String> peliculas = new ArrayList<>();

    public Pelicula(String id, String titulo, String director, String precio) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.precio = precio;
    }

    public Pelicula() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public List<String> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(List<String> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", precio='" + precio + '\'' +
                ", peliculas=" + peliculas +
                '}';
    }

    //metodo para añadir 5 peliculas por defecto
    public void añadirPeliculasPD(){
        peliculas.add("1-El señor de los anillos-Peter Jackson-2021");
        peliculas.add("2-La conquista de los mares-John Doe-2022");
        peliculas.add("3-El último aliento-Jane Smith-2023");
        peliculas.add("4-El enigma del tiempo-Charlie Brown-2024");
        peliculas.add("5-El jardín secreto-Emily Davis-2025");
    }

    public String consultarPeliculaPorId(int i) {
        return peliculas.get(i-1);
    }

    public String consultarPeliculaPorTitulo(String titulo) {
        String pelicula = "";
        for (String p : peliculas) {
            if (p.contains(titulo)) {
                pelicula = p;
            }
        }
        return pelicula;
    }

    public String consultarPeliculaPorDirector(String director) {
        String pelicula = "";
        for (String p : peliculas) {
            if (p.contains(director)) {
                pelicula = p;
            }
        }
        return pelicula;
    }

    public void agregarPelicula(String pelicula) {
        peliculas.add(pelicula);
    }
}
