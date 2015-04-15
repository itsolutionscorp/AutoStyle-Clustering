from functools import reduce

class Series:
	def __init__(self, str):
		self.str = str

	def slices(self, count):
		if count > len(self.str):
			raise ValueError("Invalid slice length for this series: {0}".format(count))
		out = []
		for ix in range(0, len(self.str) - count + 1):
			out.append( list ( map( int, list ( self.str[ ix : ix+count ] ) ) ) )
		return out

	def largest_product(self, count):
		if count == 0:
			return 1
		mul = lambda a,b: a*b
		mul_all = lambda l: reduce(mul, l)
		return max( map( mul_all, self.slices(count) ) )

if __name__ == '__main__':
	print(Series('0123').slices(2))
	print(Series('0123').largest_product(2))
