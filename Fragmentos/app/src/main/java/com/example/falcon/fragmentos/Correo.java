package com.example.falcon.fragmentos;

/**
 * Created by Falcon on 3/25/16.
 */
public class Correo
{
    private String de;
    private String asunto;
    private String texto;

    public Correo(String de, String asunto, String texto){
        this.de = de;
        this.asunto = asunto;
        this.texto = texto;
    }

    public String getDe(){
        return de;
    }

    public String getAsunto(){
        return asunto;
    }

    public String getTexto(){
        return texto;
    }
}