package TransitAPI;

public class MetroStationFeatures {
    private int CODI_PARADA;
    private String NOM_PARADA;
    private int ID_ESTACIO_LINIA;
    private int CODI_ESTACIO_LINIA;
    private int CODI_GRUP_ESTACIO;
    private int ID_ESTACIO;
    private int CODI_ESTACIO;
    private String NOM_ESTACIO;
    private int ORDRE_ESTACIO;
    private int ID_LINIA;
    private int CODI_LINIA;
    private String NOM_LINIA;
    private int ORDRE_LINIA;
    private int ID_TIPUS_SERVEI;
    private String DESC_SERVEI;
    private String ORIGEN_SERVEI;
    private String DESTI_SERVEI;
    private int ID_TIPUS_ACCESSIBILITAT;
    private String NOM_TIPUS_ACCESSIBILITAT;
    private String DATA_INAUGURACIO;
    private String DATA;
    private String COLOR_LINIA;
    private String PICTO;
    private String PICTO_GRUP;
    private int ID_TIPUS_ESTAT;
    private String NOM_TIPUS_ESTAT;

    @Override
    public String toString() {
        return "FeatureProperties{" +
                "ID_ESTACIO_LINIA=" + ID_ESTACIO_LINIA +
                ", CODI_ESTACIO_LINIA=" + CODI_ESTACIO_LINIA +
                ", CODI_GRUP_ESTACIO=" + CODI_GRUP_ESTACIO +
                ", ID_ESTACIO=" + ID_ESTACIO +
                ", CODI_ESTACIO=" + CODI_ESTACIO +
                ", NOM_ESTACIO='" + NOM_ESTACIO + '\'' +
                ", ORDRE_ESTACIO=" + ORDRE_ESTACIO +
                ", ID_LINIA=" + ID_LINIA +
                ", CODI_LINIA=" + CODI_LINIA +
                ", NOM_LINIA='" + NOM_LINIA + '\'' +
                ", ORDRE_LINIA=" + ORDRE_LINIA +
                ", ID_TIPUS_SERVEI=" + ID_TIPUS_SERVEI +
                ", DESC_SERVEI='" + DESC_SERVEI + '\'' +
                ", ORIGEN_SERVEI='" + ORIGEN_SERVEI + '\'' +
                ", DESTI_SERVEI='" + DESTI_SERVEI + '\'' +
                ", ID_TIPUS_ACCESSIBILITAT=" + ID_TIPUS_ACCESSIBILITAT +
                ", NOM_TIPUS_ACCESSIBILITAT='" + NOM_TIPUS_ACCESSIBILITAT + '\'' +
                ", DATA_INAUGURACIO='" + DATA_INAUGURACIO + '\'' +
                ", DATA='" + DATA + '\'' +
                ", COLOR_LINIA='" + COLOR_LINIA + '\'' +
                ", PICTO='" + PICTO + '\'' +
                ", PICTO_GRUP='" + PICTO_GRUP + '\'' +
                ", ID_TIPUS_ESTAT=" + ID_TIPUS_ESTAT +
                ", NOM_TIPUS_ESTAT='" + NOM_TIPUS_ESTAT + '\'' +
                '}';
    }

    public String getDATA_INAUGURACIO() {
        return DATA_INAUGURACIO;
    }

    public String getNOM_ESTACIO() {
        return NOM_ESTACIO;
    }

    public String getNOM_LINIA() {
        return NOM_LINIA;
    }

    public int getCODI_ESTACIO() {
        return CODI_ESTACIO;
    }

    public int getCODI_PARADA() {
        return CODI_PARADA;
    }

    public String getNOM_PARADA() {
        return NOM_PARADA;
    }
}
