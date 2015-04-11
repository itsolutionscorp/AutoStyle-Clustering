def sum_of_multiples(limit, factors=None):
	return reduce(lambda s, c: s + c, _multiples(limit, factors), 0)

def _multiples(limit, factors):
	""" Generates all multiples of `factors` from 1 up to, but not including `limit`
	"""
	if not factors:
		factors = [3, 5]
	for candidate in range(1, limit):
		for f in factors:
			if f > 0 and candidate % f == 0:
				yield candidate
				break
