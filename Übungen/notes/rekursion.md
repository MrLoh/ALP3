
**Lösung der Rekursionsgleichung:**
T(n) = T(n-1) + 1
T(0) = 0

## 1. Einsetzen:
T(n) = T(n-1) + 1
     = T(n-2) + 1 + 1
     = T(n-3) + 1 + 1 + 1

## 2. Muster finden:
T(n) = T(n-k) + Sum_i=1^k 1
     = T(n-k) + k

## 3. Anker finden:
Bei welchem k tritt der Basecase 0 ein?
n-k = 0  <=>  k = n

## 4. Anker einsetzen
T(n) = T(n-n) + n
     = T(0) + n
     = n
     in O(n)


**Beispiel 2:**
T(n) = T(n/2) + n
T(1) = 1

**1:**
T(n) = T(n/2) + n
     = T(n/4) + n/2 + n
     = T(n/8) + n/4 + n/2 + n
**2:**
     = T(n/2^k) + Sum_{i=0}^{k-1} n/2^i
**3:**
n/2^k = 1  <=>  k = log(n)
**4:**
     = T(n/2^log(n)) + Sum_{i=0}^{log(n)-1} n/2^i
     = 1 + Sum_{i=0}^{log(n)-1} (n/2)^i
     = Sum_{i=0}^{log(n)} (n/2)^i
     = n * Sum_{i=0}^{log(n)} (1/2)^i
     = n * ( (1-1/2)^(log(n)+1)/(1-1/2) )
     = n * ( 2*(1-1/2^(log(n)+1) )
     = n * ( 2-1/2^(log(n)) )
     = n * ( 2-1/n )
     = 2n - 1
     in O(n)


## Geometrische Reihe: H(q,m) = Sum_i=0^m q^i = (1-q)^(m+1)/(1-q)
