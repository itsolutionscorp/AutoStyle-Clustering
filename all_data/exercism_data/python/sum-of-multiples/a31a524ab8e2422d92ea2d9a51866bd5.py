'''sum_of_multiples.py
	created 6 Oct 2014
	by @jestuber '''

class SumOfMultiples(object):
	"""Finds the sum of all multiples of 3 or 5 up to given n.
		Multiples besides 3 and 5 can be input"""
	def __init__(self, *multOf):
		super(SumOfMultiples, self).__init__()
		self.multOf = multOf or [3,5]

	def to(self, n):
		multiples = [x for x in range(n) if any(x % m == 0 for m in self.multOf)]
		return sum(multiples)
