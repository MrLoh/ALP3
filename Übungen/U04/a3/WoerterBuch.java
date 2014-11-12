import java.util.Collection;

public interface WoerterBuch<S,W> {
	//wichtige Operationen
	public Eintrag<S,W> finde(S schluessel);
	public void einfuege (Eintrag<S,W> e);
	public void streiche (S schluessel);

	// weitere nützliche Operationen
	public int groesse();
	public boolean istLeer();
	public Collection<Eintrag<S,W>> eintraege();
}

