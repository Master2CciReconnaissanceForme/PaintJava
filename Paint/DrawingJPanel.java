package testDisplayImage;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DrawingJPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/* OBJECTIFS :
	 * - afficher l'image binaire d'entrée ENREGISTREE SOUS UN AUTRE NOM (copie !) 
	 * - donner la possibilité de tracer des points / courbes / lignes 
	 * 	 avec la souris (coordonnées du clic à récupérer et affichage instantané de la modif)
	 * - deux couleurs : noir et blanc --> bouton choix couleur
	 * - taille de pinceau
	 * - pouvoir annuler les modifications faites --> bouton annuler dernière action
	 * - pouvoir zoomer/dézoomer la zone de dessin ? --> boutons zoom in / out
	 * - pouvoir remplir la forme dont le contour fermé a été défini 
	 * - ajouter barre de défilement ! */
	
	public MatriceDrawing matriceDrawing ;
	public int brushSize = 1 ;
	public int color = 1 ;
	
	public DrawingJPanel(MatriceDrawing matriceDrawing) {
	
		this.matriceDrawing = matriceDrawing ;
	
		JButton annuler = new JButton("Annuler");
		annuler.setSize(100, 20);
		add(annuler); 
		/* action listener pour annuler les actions en sens inverse, 
		 * il faudrait pouvoir TOUT annuler donc garder une trace de chaque action
		 * et faire les actions avec la couleur inverse (pour les points et segments)
		 */
		
		JRadioButton noir = new JRadioButton("Noir");
		JRadioButton blanc = new JRadioButton("Blanc");
		ButtonGroup colorChoice = new ButtonGroup();
		colorChoice.add(noir);
		colorChoice.add(blanc);
		blanc.setSelected(true);
		add(noir);
		add(blanc);	
		
		blanc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				color = 1 ;			
			}
		});
		
		noir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				color = 0 ;			
			}
		});
			
		Choice action = new Choice();
		action.addItem("pinceau");
		action.addItem("sélection rectangle"); // si on peut ajouter des formes pour faire des remplissages ou quoi
		add(action);
		
		final Choice size = new Choice();
		for (int s = 1 ; s < 500 ; s++)
			size.addItem(""+s);
		add(size);
		
		size.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				brushSize = size.getSelectedIndex() ;
				/* on récupère l'index de la taille dans le menu déroulant 
				 * taille 1 sélectionnée <=> size = 0 ;
				 * permet de gérer la "propagation" pour les pixels à colorer
				 * taille 1, on colore le pixel et 0 autour
				 * taille 2, on colore le pixel + tous les pixels autour (1 couche)
				 * taille 3, on colore le pixel + les picels autour niveau 1 + la couche suivante (2 couches)
				 * donc à chaque taille, on a "size" couches (cf la fonction setWhitePoint)
				 */
			}
		});
		
		addMouseListener(new DrawingMouseListener(this));
		addMouseMotionListener(new DrawingMouseListener(this));


	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//A chaque repaint, on met à jour l'image qui a été modifiée
		g.drawImage(matriceDrawing.imageCopy,0,40, matriceDrawing.imageCopy.getWidth(null), matriceDrawing.imageCopy.getHeight(null), this);
	}
	

	
	
}
