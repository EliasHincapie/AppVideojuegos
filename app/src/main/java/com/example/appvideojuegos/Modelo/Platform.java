package com.example.appvideojuegos.Modelo;

import java.io.IOException;
import java.io.Serializable;

public enum Platform implements Serializable {
    PC_WINDOWS, PC_WINDOWS_WEB_BROWSER, WEB_BROWSER;
    // Metodo para convertir el enum a su representación en String

    public String toValue() {
        switch (this) {
            case PC_WINDOWS: return "PC (Windows)";
            case PC_WINDOWS_WEB_BROWSER: return "PC (Windows), Web Browser";
            case WEB_BROWSER: return "Web Browser";
        }
        return null;
    }
    // Metodo estático para convertir un String en el correspondiente valor del enum
    public static Platform forValue(String value) throws IOException {
        if (value.equals("PC (Windows)")) return PC_WINDOWS;
        if (value.equals("PC (Windows), Web Browser")) return PC_WINDOWS_WEB_BROWSER;
        if (value.equals("Web Browser")) return WEB_BROWSER;
        throw new IOException("Cannot deserialize Platform");
    }
}
