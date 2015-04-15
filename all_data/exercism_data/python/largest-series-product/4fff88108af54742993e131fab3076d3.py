def slices(number, n):
	if n == 0:
		return []
	if len(number) < n:
		raise ValueError('Slice bigger than number')
	return [[int(v) for v in number[i:i+n]] for i in xrange(len(number) - n + 1)]
		
		
def largest_product(number, n):
	max_product = 1
	ss = slices(number, n)
	for s in ss:
		product = 1
		for v in s:
			product *= v
		if product > max_product:
			max_product = product
	return max_product
