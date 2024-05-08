package com.musicaly.musicaly.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicaly.musicaly.model.Genero;
import com.musicaly.musicaly.model.Singer;
import com.musicaly.musicaly.model.Song;
import com.musicaly.musicaly.repositories.SingerRepository;
import com.musicaly.musicaly.repositories.SongRepository;

public class Principal {

    Scanner teclado = new Scanner(System.in);
    SingerRepository singerRepository;
    SongRepository songRepository;

    public Principal(List<JpaRepository> repositorios) {
        this.singerRepository = (SingerRepository) repositorios.get(0);
        this.songRepository = (SongRepository) repositorios.get(1);
    }

    public void lanzarMenu() {
        String menu = """
                1 - Registrar un nuevo cantante
                2 - Registrar una nueva cancion
                3 - Buscar cancion por cantante
                4 - Mostrar lista de canciones
                5 - Mostrar lista de cantantes
                """;
        System.out.println(menu);
        int eleccion = Integer.parseInt(teclado.nextLine());
        switch (eleccion) {
            case 1:
                registrarCantante();
                break;
            case 2:
                registrarCancion();
                break;
            case 3:
                buscarCancionXCantante();
                break;
            case 4:
                mostrarListaDeCanciones();
                break;
            case 5:
                mostrarListaDeArtistas();
                break;
            default:
                break;
        }
    }

    private void mostrarListaDeArtistas() {
        List<Singer> cantantes = singerRepository.findAll();
        cantantes.forEach(c -> c.getSongs().stream().forEach( s -> System.out.println("Cantante: "+c.getName()+"compuso "+s.getName())));
        lanzarMenu();
    }

    private void mostrarListaDeCanciones() {
       List<Song> canciones = songRepository.findAll();
       canciones.forEach(c -> System.out.println(c.getName()+" - "+c.getSinger().getName()));
       lanzarMenu();
    }

    private void registrarCantante() {
        System.out.println("Ingrese el nombre del cantante");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese el apellido del cantante");
        String apellido = teclado.nextLine();
        System.out.println("Ingrese la edad del cantante");
        int edad = Integer.parseInt(teclado.nextLine());
        System.out.println("Ingrese el genero del cantante");
        Genero sexo = Genero.fromString(teclado.nextLine());
        try {
            Singer cantante = new Singer(nombre, apellido, edad, sexo);
            if (singerRepository.save(cantante) != null) {
                System.out.println("Se guardo exitosamente el cantante!!");
            } else {
                System.out.println("No se guardo correctamente");
            }
        } catch (Exception e) {
            e.getMessage();
        }
        lanzarMenu();
    }

    private void registrarCancion() {
        System.out.println("Ingrese el nombre de la cancion");
        String nombre = teclado.nextLine();
        System.out.println("Ingrese la duracion de la cancion");
        String duracion = teclado.nextLine();
        System.out.println("Ingrese la descripcion de la cancion");
        String descripcion = teclado.nextLine();
        System.out.println("Quien compuso está musica?");
        try {
            Singer cantanteBuscado = buscarCantante();
            Song musicaInsertar = new Song(nombre, duracion, descripcion, cantanteBuscado);
            if (songRepository.save(musicaInsertar) != null) {
                System.out.println("Se guardó exitosamente la canción y se asignó al artista");
            } else {
                System.out.println("No se completó la acción");
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        lanzarMenu();
    }

    private Singer buscarCantante() {
        List<Singer> cantantes = singerRepository.findAll();
        cantantes.forEach(c -> System.out.println(c.getId() + " " + c.getName()));
        System.out.println("Ingrese el numero del cantante");
        Long idCantante = Long.parseLong(teclado.nextLine());
        Optional<Singer> cantante = singerRepository.findById(idCantante);
        return cantante.get();
    }

    private void buscarCancionXCantante() {
        System.out.println("Elija de la lista el cantante que desea buscar alguna musica");
        Singer cantanteBuscado = buscarCantante();
        Optional<List<Song>> musicaBuscada = Optional.of(singerRepository.obtenerCanciones(cantanteBuscado.getName()));
        if (musicaBuscada.isPresent()) {
            musicaBuscada.get().stream().forEach(m -> System.out.println(m.toString()));    
        }else{
            System.out.println("No hay ni pincho");
        }
        lanzarMenu();
    }
}
