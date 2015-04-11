'''roman.py
	created 10 Oct 2014
	by @jestuber '''

numerals = (('M',  1000),
            ('CM', 900),
            ('D',  500),
            ('CD', 400),
            ('C',  100),
            ('XC', 90),
            ('L',  50),
            ('XL', 40),
            ('X',  10),
            ('IX', 9),
            ('V',  5),
            ('IV', 4),
            ('I',  1))

def numeral(arabic):
		roman = []
		for letter, integer in numerals:
			while arabic >= integer:
				roman.append(letter)
				arabic -= integer
		return ''.join(roman)
