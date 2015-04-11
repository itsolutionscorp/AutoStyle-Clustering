roman_numerals = 'IVXLCDMxx'
patterns = [
	'', '{0}', '{0}{0}', '{0}{0}{0}', '{0}{1}',
	'{1}', '{1}{0}', '{1}{0}{0}', '{1}{0}{0}{0}', '{0}{2}'
]

def numeral(number):

	digits = map(int, str(number))

	res = []
	for index, digit in enumerate(reversed(digits)):
		args = list(roman_numerals[2*index : 2*index+3])
		res.append(patterns[digit].format(*args))

	return ''.join(reversed(res))
