### (a)

**1. D[i,j] = min( D[i,j], max(D[i,k], D[k,j]) )**



**2. D[i,j] = max( D[i,j], min(D[i,k], D[k,j]) )**

Wenn die Kanten die Bandbreite einer Übertragung zwischen zwei Routern angeben, dann findet der Algorithmus die Routen mit maximale Bandbreite (also minimalem Bottelneck). Initialisierung erfolgt bei keiner Verbindung zweier Knoten mit 0 anstatt von ∞.