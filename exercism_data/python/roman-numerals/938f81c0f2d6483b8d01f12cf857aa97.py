from __future__ import division
numerals = ['I', 'V', 'X', 'L', 'C', 'D', 'M', '', '']
def numeral(arabic):
	digits = []

	while arabic > 0:
		digits.append(arabic % 10)
		arabic //= 10

	roman = ""
	for i in range(len(digits)):
		d = digits[i]
		a, b, c = numerals[i*2:i*2+3]
		if d == 9:
			roman += c + a
		elif d > 4:
			roman += a * (d - 5) + b
		elif d == 4:
			roman += b + a
		else:
			roman += a * d

	return roman[::-1]
