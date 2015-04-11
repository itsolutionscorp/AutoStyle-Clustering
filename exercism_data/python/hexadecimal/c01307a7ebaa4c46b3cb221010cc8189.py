def hexa(s):
	result = 0
	for d in s.lower():
		if d not in _digits:
			raise ValueError("Not a hex string")
		result = (result << 4) + _digits[d]
	return result

_digits = {str(d): d for d in range(10)}
_digits.update({ 'a': 10, 'b': 11, 'c': 12, 'd': 13, 'e': 14, 'f': 15 })
