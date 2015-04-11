import re

tr = {"I": 1, "X": 10, "C": 100, "M": 1000,
	  "V": 5, "L": 50, "D": 500,
	  "IX": 9, "XC": 90, "CM": 900,
	  "IV": 4, "XL": 40, "CD": 400}

tr_to_roman = dict((v, k) for k, v in tr.items())
tr_to_roman[0] = ""

def _to_roman(digit, scale):
	"""
	Returns roman numeral representation of a digit 0-9
	for the case one ones, tens, and hundreds, handling the special case
	of thousands using the `tr_to_roman` lookup table.

	@param digit: number 0-9, or some non-negative integer if the scale is 1000
	@param scale: one of [1, 10, 100, 1000]
	"""
	if scale == 1000:
		return 'M' * digit
	elif digit in [1, 4, 5, 9]: # could also do [4, 9] since other cases can handle 1, 5
		return tr_to_roman[digit * scale]
	elif digit < 4:
		return tr_to_roman[1 * scale] * digit
	elif digit < 9:
		return tr_to_roman[5 * scale] + _to_roman(digit - 5, scale)

def numeral(n):
	res = ""
	for base in [1000, 100, 10, 1]:
		res += _to_roman(n / base, base)
		n %= base
	return res


def to_arabic(s):
	s = s.strip()
	if re.match(r"[IXCMVLD]+$", s) is None:
		raise ValueError("Invalid input string")
	i = 0
	res = 0
	while i < len(s):
		dbl = s[i:i+2]
		if dbl in tr:
			res += tr[dbl]
			i += 2
		else:
			res += tr[s[i:i+1]]
			i += 1
	return res
