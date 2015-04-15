import operator

def prod(iterable):
	return reduce(operator.mul, iterable, 1)

class Series:
	def __init__(self, series):
		self.series = series
		
	def slices(self, sliceLength):
		seriesLength = len(self.series)
		
		if (sliceLength < 1 or sliceLength > seriesLength):
			raise ValueError("Invalid slice length for this series: {}".format(sliceLength))
		
		return [[int(i) for i in self.series[n:n+sliceLength]] for n in range(seriesLength - sliceLength + 1)]
		
	def largest_product(self, sliceLength):
		if (sliceLength == 0): return 1
		
		slices = self.slices(sliceLength)
		
		return max(prod(slice) for slice in slices)
