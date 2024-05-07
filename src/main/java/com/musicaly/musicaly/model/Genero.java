package com.musicaly.musicaly.model;

public enum Genero {

    M("masculino", "M"), 

    F("femenino", "F");

    String generoEspanol;

    String generoAbreviacion;

    private Genero(String generoEspanol, String generoAbreviacion) {
        this.generoEspanol = generoEspanol;
        this.generoAbreviacion = generoAbreviacion;
    }

    public static Genero fromString(String palabra){

        for (Genero genero : Genero.values()) {
            if (genero.generoEspanol.equalsIgnoreCase(palabra) || genero.generoAbreviacion.equalsIgnoreCase(palabra)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("No se encontró el genero brindado");

    }




}
