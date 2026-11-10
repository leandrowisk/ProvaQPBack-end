package com.qpLeandro.QPProva.Utils;

import java.util.Locale;

public class PessoaUtil {
    public static String obterNacionalidadeFormatada(String siglaPais) {
        if (siglaPais == null || siglaPais.isBlank()) {
            return "Provavel nacionalidade desconhecida";
        }
        Locale locale = new Locale("", siglaPais.toUpperCase());
        String nomePais = locale.getDisplayCountry(new Locale("pt", "BR"));

        if (nomePais.isBlank() || nomePais.equals(siglaPais)) {
            return "Provavel nacionalidade desconhecida";
        }
        return "Provável nacionalidade: " + nomePais;
    }

}
