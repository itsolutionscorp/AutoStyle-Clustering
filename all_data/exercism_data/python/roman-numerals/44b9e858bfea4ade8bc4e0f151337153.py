from math import log10 as log

def numeral(arabic):
	numerals_10 = ('I','X','C','M')
	numerals_5 = ['V','L','D']
	roman_num = []
	for place, digit in enumerate(str(arabic)[::-1]):
		det = int(digit)%5
		if int(digit) < 5:
			roman_num += [numerals_10[place]*det] if det < 4 else  [numerals_10[place] + numerals_5[place]]
		elif int(digit) > 5:
			roman_num += [numerals_5[place] + numerals_10[place]*det] if det < 4 else  [numerals_10[place] + numerals_10[place + 1]]
		else:
			roman_num += [numerals_5[place]]
	return ''.join(roman_num[::-1])
