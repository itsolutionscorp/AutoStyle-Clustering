def numeral(n):
	digit_sets = [
		('I', 'V', 'X'),
		('X', 'L', 'C'),
		('C', 'D', 'M'),
		('M', '?', '?')
	]
	return "".join(
		_convert_single(digit, *digit_sets.pop())
			for digit in str(n).zfill(4)
	)


def _convert_single(n, s1, s5, s10):
	""" Convert a number from 0 to 9 (represented as string) to roman
		using custom strings for I, V, X.
	"""
	return {
		'0': '',
		'1': '{I}',
		'2': '{I}{I}',
		'3': '{I}{I}{I}',
		'4': '{I}{V}',
		'5': '{V}',
		'6': '{V}{I}',
		'7': '{V}{I}{I}',
		'8': '{V}{I}{I}{I}',
		'9': '{I}{X}'
	}[n].format(I=s1, V=s5, X=s10)
