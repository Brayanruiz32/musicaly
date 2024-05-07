package com.musicaly.musicaly.principal;

import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musicaly.musicaly.model.Genero;
import com.musicaly.musicaly.model.Singer;
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

            default:
                break;
        }

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
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        Singer cantante = new Singer(nombre, apellido, edad, sexo);


        if (singerRepository.save(cantante) != null) {
            System.out.println("Se guardo exitosamente el cantante!!");
        }else{
            System.out.println("No se guardo correctamente");
        }
    }


    private void registrarCancion() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'registrarCancion'");
    }
    private void buscarCancionXCantante() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'buscarCancionXCantante'");
    }


}
