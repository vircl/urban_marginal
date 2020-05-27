package controleur;
/**
 * Interface Global 
 * 
 * D�finition des constantes utilis�es par l'application
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
	public static final int    PORT       = 6666;
	public static final String DEFAULT_IP = "127.0.0.1";
	
	/**
	 * Medias
	 */
	public static final String SEPARATOR  = "//";
	public static final String CHEMIN     = "media" + SEPARATOR;
	public static final String FONDS      = CHEMIN + "fonds" + SEPARATOR;
	public static final String FOND_CHOIX = FONDS + "fondchoix.jpg";
	public static final String PERSOS     = CHEMIN + "personnages" + SEPARATOR;
	public static final String PERSO      = PERSOS + "perso";
	public static final String EXTENSION  = ".gif";
	
	/**
	 * Personnages
	 */
	public static final int    GAUCHE     = 0;
	public static final int    DROITE     = 1;
	public static final String MARCHE     = "marche";
	public static final String BLESSE     = "touche";
	public static final String MORT       = "mort";
	public static final int    NB_PERSOS  = 3;
	public static final int    H_PERSO    = 44;
	public static final int    L_PERSO    = 39;
	
	/**
	 * Arene
	 */
	public static final int    H_ARENE    = 600;
	public static final int    L_ARENE    = 800;
	public static final int    H_CHAT     = 200;
	public static final int    H_SAISIE   = 25;
	public static final int    MARGE      = 5;
	public static final String FOND_ARENE = FONDS + "fondarene.jpg";
	
	/**
	 * Murs
	 */
	public static final int    NB_MURS    = 20;
	public static final String MURS       = CHEMIN + "murs" + SEPARATOR;
	public static final String MUR        = MURS + "mur.gif";
	public static final int    H_MUR      = 35;
	public static final int    L_MUR      = 34;
	
	/**
	 * Messages
	 */
	public static final String SEPARE     = "�";
	public static final int    PSEUDO     = 0;
}
