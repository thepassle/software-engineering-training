public class SogyoSudoku {
    public static void main(String[] args){

    	String input = getInput(args);

        Sudoku game = new Sudoku(input);
        game.solve();
	}

	public static String getInput(String[] args){
    	String input = "";
		
		if(args.length != 1){
			throw new IllegalArgumentException("Input moet 81 cijfers zijn");
		}

        for (String val : args) {
    		if(val.length() != 81){
		        throw new IllegalArgumentException("Input moet 81 cijfers zijn");
			} else {
				input = val;
			}
        }
        return input;
	}
}


/**		Sudoku input:

		000 820 090
		500 000 000
		308 040 007

		100 000 040
		006 402 503
		000 090 010

		093 004 000
		004 035 200
		000 700 901
		
000000000000000000000000000000000000000000000000000000000000000000000000000000000
000820090500000000308040007100000040006402503000090010093004000004035200000700900

*/