public class a3b {
	static int I = Integer.MAX_VALUE; //Infinity symbol

	// We notice that k can only be the last vertex in an optimal path that ends at k, therefore the path length remains the same if we allow k to be an intermediate vertex. Therefor d_ij^k=d_ij^(k-1), thus we just need one Matrix.

	public static int[][] floydWarshall(int[][] g) {
		int n = g.length;
		int[][] d = new int[n][n];
		System.arraycopy(g, 0, d, 0, n);
		for ( int k=0; k<n; k++ ) {
			for ( int i=0; i<n; i++ ) {
				for ( int j=0; j<n; j++ ) {
					if ( d[i][k] != I && d[k][j] != I ) //avoid int overflow
						d[i][j] = Math.min(d[i][j], d[i][k]+d[k][j]);
				}//i
			}//j
		}//k
		return d;
	}

	public static void printTable(int[][] m) {
		int n = m.length;
		for ( int i=0; i<n; i++ ) {
			for ( int j=0; j<n; j++ ) {
				if( m[i][j] == I )
					System.out.printf(" \u221e ");
				else
					System.out.printf("%2d ", m[i][j]);
			}//j
			System.out.printf("\n");
		}//i
	}



	public static void main(String[] args) {

		//Graph aus Aufgabe 1
		int[][] g1 = {//s u v x y z
                       {0,3,I,6,I,I}, //s
                       {I,0,6,1,I,8}, //u
                       {I,I,0,I,1,1}, //v
                       {I,2,4,0,4,I}, //x
                       {4,I,5,I,0,I}, //y
                       {I,I,I,I,I,0}};//z

		//Graph aus Skript S.88
		int[][] g2 = {//s u v x y
                       {0,9,I,5,I}, //s
                       {I,0,1,5,I}, //u
                       {I,I,0,I,6}, //v
                       {I,3,1,0,2}, //x
                       {7,I,4,I,0}};//y

		System.out.println("Distances g1:");
		printTable(floydWarshall(g1));

		System.out.println("\nDistances g2:");
		printTable(floydWarshall(g2));

	}
}


