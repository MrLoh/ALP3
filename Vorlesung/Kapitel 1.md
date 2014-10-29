KAPITEL 1: ALGORITHMEN ENTWURF UND ANALYSE
==========================================



# Beispiel Sortieralgorithmen
Entwurf und Analyse von Algorithmen, Bsp. Sortieren und Auswählen.

## Implementierung

Gegeben ist eine Eingabefolge `a1, ..., an` von paarweise vergleichbaren Objekten. Die Vergeleichsoperation sei mit `≤` bezeichnet. (Bsp. Zahlen, Buchstaben, Strings, ...)

Gesucht ist ein Algorithmus, der die Objekte in aufsteigender Regenfolge sortiert. D.h. `a_{i,1}, ... a_{i,n}` mit `a_{i,j} ≤ a_{i,j+1}` mit `j in [1..n-1]`

#### Algorithmus 1 (Selectionsort)
* Falls `n=1`, gib dieses Element zurück.
* Sonst:
    - Durchlaufe Folge von links nach rechts und finde ihr Maximum.
    - Plaziere dieses an der rechtesten Stelle.
    - Durchlaufe Restfolge der Größe `n-1` und iteriere.

#### Algorithmus 2 (Mergesort)
* Falls `n=1`, gib dieses Element zurück.
* Sonst:
    - Teile Folge in zwei Hälften der Größe `n/2`.
    - Sortiere die Hälften rekursiv.
    - Packe sortierte Hälften zusammen zu einer sortierten Gesamtfolge.

#### Algorithmus 3 (Quicksort)
* Falls `n=1`, gib dieses Element zurück.
* Sonst:
    - Wähle eine Element `a` aus der Folge.
    - Bilde Teilfolge `S1` aller Elemente, welche kleiner `a` sind, und `S2` aller Elememnte, welche größer `a` sind.
    - Sortieren `S1` und `S2` rekursiv.
    - Gib aus die Teilfolgen aus in der Reihenfolge: `S1`, `a`, `S2`.

#### Algorithmus 4 (Bogosort)
* Erzeugen systematisch alle Permutationen der Eingabefolge.
* Für jede davon: Prüfe ob permutierte Folge aufsteigend sortirt ist. Falls ja: Gib diese zurück .
* Sonst: iteriere.


-------------------------------------------------------------------------------

## Effizienzvergleich

Frage: wie vergleichen sich Algorithmus 1-4 bezüglich der Effizienz bezüglich der Laufzeit als Funktion der Größe der Eingabe `n`.Können wir das schon abschätzen ohne die Algorithmen konkret zu Implementieren?

#### Algorithmus 1 (Selectionsort)

`C(1) = 0`
`C(n) = n-1 + n-2 + ... + 1 + 0 = \sum_{i=1}^{n-1} n(n-1)/2 = n^2/2 - n/2`

Damit habt Selection Sort eine Komplezität in `O(n)`

#### Algorithmus 2 (Mergesort)

Die Annahme das `n=2^k` (also eine Zweierpotenz) ist, macht diese Berechnung einfacher.
```
C(1) ≤ 0
C(n) ≤ 2*C(n/2) + n
     ≤ 2*(2*C(n/4) + n/2) + n
     = 4*C(n/4) + n
     ...
     ≤ 2^l*C(n/(2^l)) + l*n
```

Für `l = log(n)` wird `C(n/2^l) = C(1) = 0`, damit ergibt sich für Mergesort eine Komplexität in `O(n*log n)`

#### Algorithmus 4 (Bogosort)

Es gibt `n!` Permutationen der Eingabefolge. Im besten Fall ist die 1. Permutation richtig, also werden `n-1` Vergeleiche gebraucht. Im schlechtesten Fall ist die letzt Permutation richtig, also werden `n!*(n-1)` Vergleiche gebraucht. Im Mittel werden dann `n!*(n-1)/2` Vergleiche gebraucht.

#### Algorithmus 3 (Quicksort)

Die Effizienz hängt von der Auswahl von a ab.

**Schlimmster Fall:**
Im schlimmsten Fall ist `a` immer das größte Element, (z.B. immer das erste Element wird genommen bei einer absteigend sortierten Folge). Dann folgt, dass `|S1| = n-1`, `|S2| = 0`. Die Anzahl der Vergleiche ist dann wieder über die Gauß-Summe gegeben. Damit ergibt sich die untere Schranke `θ(n^2)`

**Mittlerer Fall**
Stattdessen wählen wir a besser zufällig aus. Dann hat jedes Element die gleiche wahrscheinlichkeit. Die mittlere Anzahl der Vergleiche ist damit gegeben durch:
```
C(0) = 0  und  C(1) = 0
C(n) = 1/n * \sum_{k=1}^n C(k-1) + C(n-k) + n-1
```
falls `a` das `k`-kleinste Element ist, so ergibt sich `|S1|=k-1` und `|S2|=n-k`. Damit folgt dann:
```
    n*C(n) = 2 * \sum_{k=0}^{n-1} C(k) + n*(n-1)
<=> (n-1)*C(n-1) = 2 * \sum_{k=0}^{n-2} C(k) + (n-1)*(n-2)
```
Durch subtraktion dieser beiden Gleichungen voneinander erhalten wir:
```
    n*C(n) - (n-1)*C(n-1) = 2 * C(n-1) + 2*(n-1)
<=> n*C(n) = (n+1)*C(n-1) + 2*(n-1)
 => C(n) ≤ (n+1)/n * C(n-1) + 2
```
Damit könne wir nun folgene Abschätzung durchführen.
```
C(n) = 1/n * \sum_(k=1)^n (C(k-1) + C(n-k)) + n-1
~>  C(n) ≤ (n+1)/n * C(n-1) + 2
         ≤ (n+1)/(n-1) * C(n-2) + 2*(n+1)/n + 2*(n+1)/(n+1)
         ≤ (n+1)/(n-2) * C(n-3) + 2*(n+1)/(n-1) 2*(n+1)/n + 2*(n+1)/(n+1)
         ...
         ≤ (n+1)/2 * C(1) + 2*( (n+1)/3 + (n+1)/4 + ... + (n+1)/(n+1))
         ≤ 2*(n+1) * (1/3 + 1/4 + ... 1/(n+1))
         ≤ 2*(n+1) * (1/1 + 1/2 + ... 1/(n+1))
         ≤ 2*(n+1) * H_{n+1}                            \ Harmonische Reihe
         ≤ 2*(n+1) * (1 + \int_1^{n+1} 1/x \dx)
         = 2*(n+1) * (1 + ln(n+1))
         ≤ 2*n*ln(n) + O(n)
         ≤ 2/log(e) * n*log(n) + O(n)
         ≈ 1.38 * n*log(n) + O(n)
```
Alle Abschätungen, die wir im Laufe der Rechnung gemacht haben, sind dabei in den Termen von `O(n)` enthalten. Damit sollte Quicksort ca. 38% langsamer als Mergesort sein, allerdings bezieht sich diese Rechung nur auf die Vergleiche. Quicksort hat aber gegenüber Mergesort den Vorteil, dass es weniger Platz braucht und weniger andere Berechnungen ausführen muss, dadurch wird es für große Folgen effektiv etwa besser als Mergesort. Beide liegen aber in `O(n*log n)`.


-------------------------------------------------------------------------------

## Einsichten

1. Beschreibung von Programmen:
    * Ein Algorithmus kann  auch umgagnssprachlich formuliert werden.
        - Gut verständlich für Menschen, schlecht für Maschinen.
    * Oder als Programm
        - Schlecht verständlich für Menschen, gut für Maschinen.
        - Außerdem gehen dabei manchmal Details verloren und die Beschreibung kann etwas verbos werden.
    * Am geeignetesten ist daher häufig Pseudocode
2. Analyse von Programmen:
    * Es ist sinnvoll die Effizienz von Algorithmen zu analysieren.
        - Die Analyse ist meist auch schon bei einer rein verbal vorliegenden Implementierung möglich.
    * Laufzeiten können im schlimmsten und im mittelfall unterschiedlich sein.
3. Algorithmus Entwurf Strategie:
    * Divide & Conquer Strategie:
        - Direkte Lösung für kleine Problemgrößen.
        - Für größere Problemgrößen, spalte auf in mittlere Teilprobleme
        - Löse diese rekursiv.
        - Kombiniere die Teillösungen zu einer Gesamtlösung.
    * Algorithmen können Zufallsentscheidungen treffen, siehe Wahl des Pivot Elements beim randomized Quicksort.
        - Sie werden dann als randomisierte Algorithmen bezeichnet.




===============================================================================

# Formalisierung des Algorithmus Begriffs

Um mathematisch haltbare Aussagen über Algorithmen zu machen, bedarf es einer Formalisierung des Algorithmus Begriffs.

Dazu gibt es verschiedene Ansätze:
* __Turingmaschine__
    - Mathematische Definition sollte aus GTI bekannt sein.
    - **Laufzeit** bei einem Eingabewort `w` ist die *Anzahl der Schritte* welche die Turingmaschine ausführt
    - **Speicherplatz** bei Eingabewort `w` ist die Anzahl der insgesamt beschriebenen Stellen.
* __Registermaschine__
    - Register enthaleten ganze Zahlen
    - Befehle zum laden, abspeichern, arithmetische Operationen, goto, bedingte Sprüge, etc.
    - Syntax und Semantik kann exact mathematische definiert werden.
    - **Laufzeit** bei einer Eingabefolge `w` (Zahlen in den ersten `n` Registern) ist die Anzahl der Schritte, bis die Maschine hält.
    - **Speicherplatz** ist die Anzahl der benutzten Register.
    - Dabei muss man zwischen zwei Ansätzen zu unterscheiden:
        + Beim *Einheitskostenmaß* sind Laufzeit und Speicherplatz unabhängig von den gängigen Werten.
        + Bei *logarithmisches Kostenmaß* sind Laufzeit-Schritte druch die Summe der Logarithmen der Eingabegrößen gegeben. Der Speicherplatz durch die Logarithmen der Speichergrößen.
        + Dies trägt der Tatsache Rechnung, dass Laufzeit und Speicher von der Größe der Zahlen abhängen.
    - Es ist zu beachten, dass bei rekursiven Funktionen ein *Laufzeitstack* benötigt wird:
        + Dann kommt die Schachtelungstiefe der Rekursion mal dem Platzbedarf eines Aufrufes zum Speicherplatz hinzu.
    - Wir verwenden aus Einfachheits-Gründen das Einheitskostenmaß und schreiben auch nicht direkt Registermaschinen-Code, sondern schreiben unsere Programme in Pseudocode.
* __Programm in höherer Programmiersprache__
    - Jede Programmiersprache hat eine klar definierte Syntax und Semantik und erlaubt daher theoretisch eine mathematisch exakte Analyse.
    - Auch Pseudocode ist im Endeffekt nichts anderes als eine höhere Programmiersprache.

Im allgemeinen wird die Komplexität nur in Abhängigkeit von der Größe der Eingabe ausgedrückt.

Formal geschieht dies über eine Funktion, die jeder Eingabe eine Größe in den natürlichen Zahlen zuordnet. (z.B. die Anzahl der Elemente beim sortieren oder die Anzahl der Bits bei zwei zu multiplizierenden Zahlen.) Die Laufzeit **im schlechtesten Fall** ist dann das Maximum der Laufzeiten bei Eingabe der Größe `n`. Die Laufzeit **im Mittel** kann bei randomisierten Algorithmen analysiert werden, zumindest in Form von `O`, `θ` oder `o`.

Die Schreibweisen `O`, `θ` und `o` sind wie folgt definiert. Seien `f` und `g` funktionen `f,g: N -> N`, dann gilt `f ∈ O(g)`, genau dann wenn `∃c > 0: f(n) ≤ g(n) ∀n`. Es gilt `f ∈ θ(g)`, genau dann wenn `f ∈ O(g)` und `g ∈ O(f)`. Es gilt `f ∈ o(g)`, genau dann wenn `lim_{n->infty} f(n)/g(n) = 0`.

Typische Funktionen bei der Analyse von Algorithmen sind
* konstant: `O(1)`
* doppellogarithmisch: `O(log log n)`
* logarithmisch: `O(log n)`
* polylogarithmisch: `log^k n`
* Wurzel: `O(n^a)` mit `a∈[0,1]`
* linear `O(n)`
* linear-logarithmisch `O(n*log n)`
* quadratisch: `O(n^2)`
* kubisch: `O(n^3)`
* polynomiell: `O(n^k)`
* exponentiell: `O(C^n)` mit `C>1`
    - Auch Fakultätsfunktion `O(n!)` wird exponentiell genannt.
    - Auch Dopplet `O(2^2^n)` wird exponentiell genannt.

Wir können `n! = 1*2*...*n` abschätzen gegen `n! ≤ n^n` und `n! > (n/2)^(n/2)` noch genauer ergibt sich `n! ~ sqrt(2π*n) * (n/e)^n`. Für `n->infty` und einem Proportionalitätsfaktor von `1`, geht das ganze `e^(1/12n)`. Damit folgt dann mit der Sterlingschen Formel `log n! ~ n*log n`.





# Das Auswahlproblem

Gegeben sei eine Folge `S` von `n` Objekten (paarweise vergleichbar) und eine ganze Zahl `k` mit `1 ≤ k ≤ n`.

Gesucht ist das `k`-kleinsten Element von `S` (also das, welches bei sortierter Folge an der Stelle `k` zu finden ist). Bei `k=1` erhielten wir beispielsweise das Minimum, bei `k=n` das Maximum und bei `k=n/2` den Median.

Eine Lösung wäre es die Folge zu sortieren, aber das kostet `θ(n*log n)` Zeit. Stattdessen kann auch ein Quicksort ähnlicher Quickselect Algorithmus verwendent werden.

```python
def quick_select(S,k):
    if n==1: return S[0]
    pivot = S[rand(size(S))]
    S_le = [x in S if x<pivot]
    S_gr = [x in S if x>pivot]
    S_eq = [x in S if x==pivot]
    if size(S_le) >= k: return quick_select(S_le,k)
    if size(S_gr)+size(S_eq) <= k: return pivot
    return quick_select(S_gr, k-size(S_le)-size(S_eq))
```

Zur Laufzeitanalyse zählen wir die Anzahl der Vergleiche (wirkliche Laufzeit ist dazu proportional).

```
T(1) = 0
T(0) = 0
schlechtester Fall: |S_le|=0 oder |S_gr|=0
dann ist der rekursive Aufruf


```
Im allgemeinen hat das Pivotelement den Rang `i` (ist `i`-kleinstes Element).
```
             / ∀k<i: T(i-1)
T(n) = n-1+ =| ∀k>i: T(n-i)
             \ ∀k=i: 0

T(n) ≤ n + max(T(i-1),T(n-i))
```
Um den Erwartungswert zu berechnen nehmen wir eine Gleichverteilung an, also wird jeder Rang `i` mit wahrscheinlichkeit `1/n` getroffen. Damit ergibt sich:
```
T(n) ≤ n + \sum_{i=1}^n 1/n * max(T(i-1),T(n-i))
     = n + 1/n * ( max(T(0),T(n-1)) + max(T(1),T(n-2)) + ...
       + max(T(n/2-1),T(n/2)) + max(T(n/2),T(n/2-1)) + ... + max(T(n-1),T(0)) )
     = n + 2/n * \sum_{i=n/2}^n T(i-1)
```
Wir gehen davon aus, dass es eine Konstante `c>0` gibt, so dass gilt `T(n) ≤ c*n`. Dies können wir durch eine Induktion behaupten.
```
IV: T(n) ≤ c*n
IA: n=0, n=1  =>  T(n) = 0
IS: T(n) ≤ n + 2/n * \sum_{i=n/2}^n T(i-1)  (mit IV => )
         ≤ n + 2/n*c * \sum_(i=n/2)^{n-1} * i
         = n + 2/n*c * ( n*(n-1)-(n/2-1)*(n/2-2) )/2
         ≤ n + 2/n*c * 0.4 * n^2
         = (1+0.8*c) * n   (für c≥5 git dann)
         ≤ (1+0.8*5) * n ≤ 5*n
```
Damit haben wir bewiesen, dass die Laufzeit von Quickselect im Mittel in `O(n)`liegt.


## Untere Schranke vergleichsbasierter Algorithmen

Bei Eingabe `S` mit Länge `|S|=n`, können wir eine untere Schranke `S≤U` für die Anzahl der Vergleiche angeben werden.

Um das Maximum von `S` zu funden, braucht es in jedem Fall `≥n-1` Vergleiche, denn erklärt ein Algorithmus `a` zum Maximum, so muss jedes Element `b≠a` in mindestens einem Vergleich das kleineregewesen sein. (Sonst könnte man `b` beliebig groß machen und Vergleiche  enden genauso wie vorher. Der Algorithmus gibt also die gleiche Antwort `a`.  Das wäre falsch, falls `b>a`.) Also müssen `≥n-1` Veglieche durchgeführt werden. Analoges gilt für das Minimum, und auch für das Ausweahlproblem als ganzes. (wobei hier die Argumentation etwas komplizierter wäre)

Die obige Betrachtung gilt natürlich nur für vergleichsbasierte Algorithmen, also solche, deren Ablauf nur von Vergleichen zwischen Elementen der Eingabefolge abhängt.

Für solche algorithmen, lässt sich ein binär Baum zeichnen, welcher als  **Vergleichsbaum** bezeichnet wird. Für Bubble Sort mit `n=3` sieht dieser Baum zum Beispiel wie folgt aus:
```
                a1≤a2
                /   \
            j /       \ n
            /           \
       a2≤a3             a1≤a3
     j /   \ n         j /   \ n
a1,a2,a3  a1≤a3    a2,a1,a3  a2≤a3
        j /   \ n          j /   \ n
   a1,a3,a2  a3,a1,a2   a2,a3,a1  a3,a2,a1
```
Der Vergleichsbaum muss mindestens `n!` Blätter haben. Denn zwei unterschiedliche Permutationen dürfen nicht zum gleichen Blatt führen. (Sonst würden die gleichen Umspeicherungs-Operationen für 2 unterschiedliche Permutationen ausgeführt und mindestens ein Fall müste falsch sein.) Ein Binärbaum der Höhe `h` hat `≤2^h` Blätter. Mit `n! ≈ (n/e)^n * sqrt(2πn)` ergibt sich also `h≥log(#Blätte)`. Also ist die Höhe des Baums `≥log(n!) = n*log(n) - O(n)`. Dies ist die Anzahl der Vergleiche im schlechtesten Fall.

**Satz:** Ein vergleichsbasierter Algorithmus benötigt zum sortieren einer Folge der Länge `n` mindestens `log(n!) = Ω(n*log n)` Vergleiche im schlechtesten Fall.

Für spezielle Ordnungen (insbesondere Zahlen), gibt es Sortieralgorithmen mit linearer Laufzeit, wie Radixsort und Bucketsort
