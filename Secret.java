/*
	Author : Faddi Susanto
	Simple Encryption and Decryption String
*/

class Secret {
	
	private String[][] phrase = {
		{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
			"0","1","2","3","4","5","6","7","8","9","`","-","=",
			"!","@","#","$","%","^","&","*","(",")",
			"[","]",";","\'","\\",",",".","/",
			"{","}",":","\"","|","<",">","?",
			"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"\n"," "},
		{"z","y","x","w","v","u","t","s","r","q","p","o","n","m","l","k","j","i","h","g","f","e","d","c","b","a",
			"9","8","7","6","5","4","3","2","1","0","=","-","~",
			")","(","*","&","^","%","$","#","@","!",
			"/",".",",","\\","\'",";","]","[",
			"?",">","<","|","\"",":","}","{",
			"Z","Y","X","W","V","U","T","S","R","Q","P","O","N","M","L","K","J","I","H","G","F","E","D","C","B","A",
			"\u00e1","\u00e9"}
	};
	
	public Secret() {}
	
	protected String Encryption(String encrypt) {
		String[] arrayEncrypt = encrypt.split("");
		encrypt = "";
		for(int i=0;i<arrayEncrypt.length;i++) {
			for(int j=0;j<this.phrase[0].length;j++) {
				if(arrayEncrypt[i].equals(this.phrase[0][j])) {
					encrypt += this.phrase[1][j];
				}
			}
		}
		return encrypt;
	}
	
	protected String Decryption(String decrypt) {
		String[] arrayEncrypted = decrypt.split("");
		decrypt = "";
		for(int i=0;i<arrayEncrypted.length;i++) {
			for(int j=0;j<this.phrase[0].length;j++) {
				if(arrayEncrypted[i].equals(this.phrase[1][j])) {
					decrypt += this.phrase[0][j];
				}
			}
		}
		return decrypt;
	}
	
}