from itertools import groupby
roman = {1: 'I', 500: 'D', 5: 'V', 1000: 'M', 100: 'C', 10: 'X', 50: 'L',
		4: 'IV', 9: 'IX', 40: 'XL', 90: 'XC', 400: 'CD', 900: 'CM'}

def numeral(arabic):
	result = []
	while arabic > 0:
		difference = 1
		for number in roman:
			if difference < number <= arabic:
				difference = number
		result.append(roman[difference])
		arabic -= difference
	return ''.join(result)
