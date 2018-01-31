class SogyoPrime {

	public static void main(String[] args){

        int primeCounter = 0;
        int i = 0;

        // figure deze loop nog ff goed uit.
        while(primeCounter < 10001){
            if( isPrimeNum(i) ){
                if(primeCounter == 10000){
                    System.out.println("The last prime nr is:");
                    System.out.println(i);
                    break;
                } else {
                    primeCounter++;
                }
            }

            i++;
        }
    }

    public static boolean isPrimeNum(int n) {
        boolean isPrime = true;

        for (int i=2; i < n; i++) {
            if (n % i == 0) {
                isPrime = false;
                break;
            }
        } 
        
        if(isPrime) {
            return true; 
        } 

        return false;  
    }
}
