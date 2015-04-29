class Series(object):
	def __init__(self, source):
		self.source = [int(i) for i in list(source)]

	def slices(self, n):
		source_len = len(self.source)
		if n < 1 or source_len < n:
			raise ValueError("Invalid slice length for this series: {}".format(n))

		return [self.source[i:i+n] for i in xrange(0, source_len - n + 1)]

	def largest_product(self, n):
		if n == 0:
			return 1
		products = [reduce(lambda x, y: x * y, s) for s in self.slices(n)]
		return reduce(lambda x, y: x if x > y else y, products)
