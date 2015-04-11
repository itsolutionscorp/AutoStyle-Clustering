def is_palindrome(i):
	i_str = str(i)
	return "".join(reversed(i_str)) == i_str

def smallest_palindrome(**kwargs):
	for product_and_factors in _gen_products(**kwargs):
		if is_palindrome(product_and_factors[0]):
			return product_and_factors

def largest_palindrome(**kwargs):
	kwargs['reverse'] = True
	for product_and_factors in _gen_products(**kwargs):
		if is_palindrome(product_and_factors[0]):
			return product_and_factors

def _gen_products(max_factor, min_factor=1, reverse=False):
	""" Generates (m*n, (m, n)) for max_factor >= m,n >= min_factor in straight or reversed order,
		only yielding unique products.
	"""
	ranges = (_gen_line(min_factor, m, reverse).__iter__() for m in range(min_factor, max_factor + 1))
	ranges = [(r.__next__(), r) for r in ranges]

	prev_product = None
	while len(ranges) > 0:
		if reverse:
			ranges = sorted(ranges, key=lambda a:-a[0][0])
		else:
			ranges = sorted(ranges, key=lambda a:a[0][0])
		product_and_factors, r = ranges[0]
		if prev_product is None or prev_product != product_and_factors[0]:
			yield product_and_factors
			prev_product = product_and_factors[0]
		try:
			ranges[0] = (r.__next__(), r)
		except StopIteration:
			ranges = ranges[1:]

def _gen_line(min_factor, max_factor, reverse):
	if reverse:
		for n in range(max_factor, min_factor-1, -1):
			yield (max_factor*n, (max_factor, n))
	else:
		for n in range(min_factor, max_factor+1):
			yield (max_factor*n, (max_factor, n))
