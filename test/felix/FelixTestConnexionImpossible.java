package felix;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextField;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.netbeans.jemmy.JemmyProperties;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JFrameOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;

import felix.Felix;
import felix.controleur.ControleurFelix;
import felix.vue.VueConnexion;

@RunWith(Parameterized.class)
public class FelixTestConnexionImpossible {

	private JFrameOperator fenetre;

	private VueConnexion vueConnexion;

	private JTextFieldOperator adresseTextField;

	private JTextFieldOperator portTextField;

	private JButtonOperator connexionButton;

	private String ip;

	private Integer port;

	private ControleurFelix controleurFelix;

	/**
	 * Configuration des parametres inject�s lors de l'appel aux tests
	 * @return
	 */
	@Parameters
	public static Collection<Object[]> params() {
	    return Arrays.asList(
	            new Object[] { "127.0.0.1", 12345},
	            new Object[] { "192.168.1.1", 50}
	        );
	}

	public FelixTestConnexionImpossible(final String ip, final Integer port) {
	    this.ip = ip;
	    this.port = port;
	}

	@Before
	public void setUp() throws InterruptedException{

		// Fixe les timeouts de Jemmy (http://wiki.netbeans.org/Jemmy_Operators_Environment#Timeouts),
		// ici : 3s pour l'affichage d'une frame.
		final Integer timeout = 3000;
		JemmyProperties.setCurrentTimeout("FrameWaiter.WaitFrameTimeout", timeout);
		JemmyProperties.setCurrentTimeout("ComponentOperator.WaitStateTimeout", timeout);

		// Cr�ation d'un mock de contr�leur.
		this.controleurFelix = EasyMock.createMock(ControleurFelix.class);
		Assert.assertNotNull(this.controleurFelix);

		// Cr�ation de la vue n�cessaire aux tests.
		// La vue s'appuie sur le mock de contr�leur.
		this.vueConnexion = new VueConnexion(controleurFelix);
		Assert.assertNotNull(this.vueConnexion);

		vueConnexion.affiche();
		//TimeUnit.SECONDS.sleep(2);
		this.recuperationVue();
	}

	private void recuperationVue(){
		// TODO Auto-generated method stub
		// Index pour la r�cup�ration des widgets.
		Integer index = 0;

		// R�cup�ration de la fen�tre de la vue de la caisse (par son titre).
		this.fenetre = new JFrameOperator(Felix.CONFIGURATION.getString("FENETRE_CONNEXION_TITRE"));
		Assert.assertNotNull("La fen�tre de la vue caisse n'est pas accessible.", this.fenetre);

		this.adresseTextField = new JTextFieldOperator(this.fenetre, index++);
		Assert.assertNotNull("Le champ de saisie de l'adresse n'est pas accessible.", this.adresseTextField);

		this.portTextField = new JTextFieldOperator(this.fenetre, index++);
		Assert.assertNotNull("Le champ de saisie du port n'est pas accessible.", this.portTextField);

		this.connexionButton = new JButtonOperator(this.fenetre, index++);
		Assert.assertNotNull("Le bouton de connexion n'est pas accessible.", this.connexionButton);
	}



	@Test
	public void ouvertureFelixTest001() throws IOException {

	}

}
