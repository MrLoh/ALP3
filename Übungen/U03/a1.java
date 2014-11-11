public interface a1 {

	/* private String inhaber;
	 * private String iban;
	 * private double kontostand;
	 * private double dispo;
	 *
	 * public void abheben(int betrag) throws EmptyAccountException {
	 		if (kontostand-betrag < -dispo)
	  			throw new EmptyAccountException();
	  		else
	 			kontostand -= betrag
	 	}
	 *
	 * public void einzahlen (double betrag) {kontostand+=betrag}
	 *
	 * public String kontostandAnzeigen() {
			return kontostand.toString();
		}
	 */


	/*Reduziert den Kontostand um den eingegebenen Betrag
	 * und wirft eine Exception, falls der Dispokredit unterschritten wird
	 */
	public void abheben(int betrag)/*throws EmptyAccountException*/;

	//Erhoeht den Kontostand um den eingegebenen Betrag
	public void einzahlen (double betrag);

	//Zeigt den Kontostand als String an
	public String kontostandAnzeigen();

}
