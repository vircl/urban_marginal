package controleur;
/**
 * Interface Global 
 * 
 * Définition des constantes utilisées par l'application
 * 
 * @project Urban Marginal
 * @package controleur
 * @version 1.0
 * @author  Virginie
 */
public interface Global {
	/**
	 * Serveur
	 */
	public static final int    PORT            = 6666;
	public static final String DEFAULT_IP      = "127.0.0.1";
	
	/**
	 * Messages
	 */
	public static final String SEPARE          = "¤";
	public static final int    PSEUDO          = 0;
	public static final int    TCHAT           = 1;
	public static final int    ACTION          = 2;
	
	
	/**
	 * Medias
	 */
	public static final String SEPARATOR       = "//";
	public static final String CHEMIN          = "media" + SEPARATOR;
	public static final String FONDS           = CHEMIN + "fonds" + SEPARATOR;
	public static final String FOND_CHOIX      = FONDS + "fondchoix.jpg";
	public static final String PERSOS          = CHEMIN + "personnages" + SEPARATOR;
	public static final String PERSO           = PERSOS + "perso";
	public static final String EXTENSION       = ".gif";
	
	/**
	 * Personnages
	 */
	public static final int    GAUCHE          = 0;
	public static final int    DROITE          = 1;
	public static final int    HAUT            = 2;
	public static final int    BAS             = 3;
	public static final int    TIRE            = 4;
	public static final String MARCHE          = "marche";
	public static final String BLESSE          = "touche";
	public static final String MORT            = "mort";
	public static final int    NB_PERSOS       = 3;
	public static final int    H_PERSO         = 44;
	public static final int    L_PERSO         = 39;
	public static final int    H_MESSAGE       = 8;
	public static final int    PAS             = 10;
	public static final int    NB_ETATS_BLESSE = 2;
	public static final int    NB_ETATS_MORT   = 2;
	public static final int    NB_ETATS_MARCHE = 4;
	public static final int    MAX_VIE         = 10;
	public static final int    GAIN_VIE        = 1;
	public static final int    PERTE_VIE       = 2;
	
	/**
	 * Arene
	 */
	public static final int    H_ARENE         = 600;
	public static final int    L_ARENE         = 800;
	public static final int    H_CHAT          = 200;
	public static final int    H_SAISIE        = 25;
	public static final int    MARGE           = 5;
	public static final String FOND_ARENE      = FONDS + "fondarene.jpg";
	
	/**
	 * Murs
	 */
	public static final int    NB_MURS         = 20;
	public static final String MURS            = CHEMIN + "murs" + SEPARATOR;
	public static final String MUR             = MURS + "mur.gif";
	public static final int    H_MUR           = 35;
	public static final int    L_MUR           = 34;
	
	/**
	 * Boules
	 */
	public static final int    H_BOULE         = 17;
	public static final int    L_BOULE         = 17;
	public static final String BOULES          = CHEMIN + "boules" + SEPARATOR;
	public static final String BOULE           = BOULES + "boule.gif";

}
