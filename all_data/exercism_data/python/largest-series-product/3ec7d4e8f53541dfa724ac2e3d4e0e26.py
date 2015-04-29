def largest_product(s, n):
	def _prod(xs):
		p = 1
		for x in xs:
			p *= x
		return p
	return max(_prod(x) for x in slices(s, n))


def slices(s, n):
	if n < 0 or n > len(s):
		raise ValueError("Bad slice length")
	res = []
	s = [int(x) for x in s]
	for i in range(len(s) - n + 1):
		res.append(s[i:i+n])
	return res
