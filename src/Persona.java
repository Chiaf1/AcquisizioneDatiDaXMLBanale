

public class Persona {

	private static final String INCREABILE = "INCREABILE";
	private String nome;
	private String cognome;
	private String sesso;
	private String comune;
	private String cmn;
	private String dataDiNascita; // AAAA/MM/GG
	private String cF;

	public Persona(Persona newPersona) {
		this.nome = newPersona.getNome();
		this.cognome = newPersona.getCognome();
		this.sesso = newPersona.getSesso();
		this.comune = newPersona.getComune();
		this.cmn = newPersona.getCmn();
		this.dataDiNascita = newPersona.getDataDiNascita();
		this.cF = calcoloCF();
	}

	public Persona(String nome, String cognome, String sesso, String comune, String cmn, String dataDiNascita) {
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.comune = comune;
		this.cmn = cmn;
		this.dataDiNascita = dataDiNascita;
		this.cF = calcoloCF();
	}

	public void setCF(String cF) {
		this.cF = cF;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getSesso() {
		return sesso;
	}

	public String getComune() {
		return comune;
	}

	public String getCmn() {
		return cmn;
	}

	public String getDataDiNascita() {
		return dataDiNascita;
	}

	public String getCF() {
		return cF;
	}
	
	public void stampa() {
		System.out.println(nome);
		System.out.println(cognome);
		System.out.println(sesso);
		System.out.println(comune);
		System.out.println(cmn);
		System.out.println(dataDiNascita);
		System.out.println(cF);	
	}

	private String calcoloCF() {
		String newCF = "";
		int i = 0, c = 0, dispari = 0, pari = 0;

		// Creazione seconde tre caratteri del codice fiscale (cognome)
		while (i < 3) {
			if (c < this.cognome.length()) {
				if (this.cognome.charAt(c) != 'A' && this.cognome.charAt(c) != 'E' && this.cognome.charAt(c) != 'I'
						&& this.cognome.charAt(c) != 'O' && this.cognome.charAt(c) != 'U') {
					i++;
					newCF += this.cognome.charAt(c);
				}

			} else {
				if (c - this.cognome.length() < this.cognome.length()) {
					if (this.cognome.charAt(c - this.cognome.length()) == 'A'
							|| this.cognome.charAt(c - this.cognome.length()) == 'E'
							|| this.cognome.charAt(c - this.cognome.length()) == 'I'
							|| this.cognome.charAt(c - this.cognome.length()) == 'O'
							|| this.cognome.charAt(c - this.cognome.length()) == 'U') {
						i++;
						newCF += this.cognome.charAt(c);
					}
				} else {
					return INCREABILE;
				}
			}
			c++;
		}

		c = 0;
		i = 0;

		// Creazione primi tre caratteri del codice fiscale (nome)
		while (i < 3) {
			if (c < this.nome.length()) {
				if (this.nome.charAt(c) != 'A' && this.nome.charAt(c) != 'E' && this.nome.charAt(c) != 'I'
						&& this.nome.charAt(c) != 'O' && this.nome.charAt(c) != 'U') {
					i++;
					newCF += this.nome.charAt(c);
				}

			} else {
				if (c - this.nome.length() < this.nome.length()) {
					if (this.nome.charAt(c - this.nome.length()) == 'A'
							|| this.nome.charAt(c - this.nome.length()) == 'E'
							|| this.nome.charAt(c - this.nome.length()) == 'I'
							|| this.nome.charAt(c - this.nome.length()) == 'O'
							|| this.nome.charAt(c - this.nome.length()) == 'U') {
						i++;
						newCF += this.nome.charAt(c);
					}
				} else {
					return INCREABILE;
				}
			}
			c++;
		}

		// calcolo anno di nascita
		newCF += this.dataDiNascita.substring(2, 4);

		// calcolo mese e conversione in lettera e calcolo giorno con controllo
		switch (Integer.valueOf(this.dataDiNascita.substring(5, 7))) {
		case 1:
			newCF += 'A';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}

			} else {
				return INCREABILE;
			}
			break;
		case 2:
			newCF += 'B';
			if (Integer.valueOf(this.dataDiNascita.substring(2, 4)) % 4 != 0) {
				if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 28
						&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 29
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 3:
			newCF += 'C';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 4:
			newCF += 'D';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 30
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 5:
			newCF += 'E';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 6:
			newCF += 'F';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 30
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 7:
			newCF += 'G';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 8:
			newCF += 'H';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 9:
			newCF += 'I';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 30
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 10:
			newCF += 'L';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 11:
			newCF += 'M';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 30
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		case 12:
			newCF += 'N';
			if (Integer.valueOf(this.dataDiNascita.substring(8, 10)) <= 31
					&& Integer.valueOf(this.dataDiNascita.substring(8, 10)) > 0) {
				if (this.sesso.charAt(0) == 'F') {
					newCF += Integer.valueOf(this.dataDiNascita.substring(8, 10)) + 40;
				} else {
					newCF += this.dataDiNascita.substring(8, 10);
				}
			} else {
				return INCREABILE;
			}
			break;
		default:
			// eventualitą di errore
			return INCREABILE;
		}

		//aggiunta codice comune
		newCF += this.cmn;
		
		//calcolo carattere di controllo
		for (i = 0; i < newCF.length(); i++) {
			if (i % 2 == 0) {
				switch (newCF.charAt(i)) {
				case '0':
					dispari += 1;
					break;
				case '1':
					dispari += 0;
					break;
				case '2':
					dispari += 5;
					break;
				case '3':
					dispari += 7;
					break;
				case '4':
					dispari += 9;
					break;
				case '5':
					dispari += 13;
					break;
				case '6':
					dispari += 15;
					break;
				case '7':
					dispari += 17;
					break;
				case '8':
					dispari += 19;
					break;
				case '9':
					dispari += 21;
					break;
				case 'A':
					dispari += 1;
					break;
				case 'B':
					dispari += 0;
					break;
				case 'C':
					dispari += 5;
					break;
				case 'D':
					dispari += 7;
					break;
				case 'E':
					dispari += 9;
					break;
				case 'F':
					dispari += 13;
					break;
				case 'G':
					dispari += 15;
					break;
				case 'H':
					dispari += 17;
					break;
				case 'I':
					dispari += 19;
					break;
				case 'J':
					dispari += 21;
					break;
				case 'K':
					dispari += 2;
					break;
				case 'L':
					dispari += 4;
					break;
				case 'M':
					dispari += 18;
					break;
				case 'N':
					dispari += 20;
					break;
				case 'O':
					dispari += 11;
					break;
				case 'P':
					dispari += 3;
					break;
				case 'Q':
					dispari += 6;
					break;
				case 'R':
					dispari += 8;
					break;
				case 'S':
					dispari += 12;
					break;
				case 'T':
					dispari += 14;
					break;
				case 'U':
					dispari += 16;
					break;
				case 'V':
					dispari += 10;
					break;
				case 'W':
					dispari += 22;
					break;
				case 'X':
					dispari += 25;
					break;
				case 'Y':
					dispari += 24;
					break;
				case 'Z':
					dispari += 23;
					break;
				}
			} else {
				if (newCF.charAt(i) < 60) {
					pari += newCF.charAt(i) - 48;
				} else {
					pari += newCF.charAt(i) - 65;
				}
			}
		}
		newCF += (char) (((pari + dispari) % 26) + 65);

		return newCF;
	}

}
