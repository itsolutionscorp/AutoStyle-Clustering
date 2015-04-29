roman_map = {
	1: 'I',
	2: 'II',
	3: 'III',
	4: 'IV',
	5: 'V',
	6: 'VI',
	7: 'VII',
	8: 'VIII',
	9: 'IX',
	10: 'X',
	20: 'XX',
	30: 'XXX',
	40: 'XL',
	50: 'L',
	60: 'LX',
	70: 'LXX',
	80: 'LXXX',
	90: 'XC',
	100: 'C',
	200: 'CC',
	300: 'CCC',
	400: 'CD',
	500: 'D',
	600: 'DC',
	700: 'DCC',
	800: 'DCCC',
	900: 'CM',
	1000: 'M'
}

def getRomanLetter(num):
	if num > 1000:
		return 'M'* (num/1000)
	else:
		return roman_map[num]

def numeral(arabic):
	roman = ''
	while(arabic):
		base = pow(10, len(str(arabic))-1)
		multiple = arabic / base
		roman += getRomanLetter(multiple*base)
		arabic -= multiple*base
	return roman
