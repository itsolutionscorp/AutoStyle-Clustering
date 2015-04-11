'''sum_of_multiples.py
	created 6 Oct 2014
	by @jestuber '''

class SumOfMultiples(object):
	"""Finds the sum of all multiples of 3 or 5 up to given n.
		Multiples besides 3 and 5 can be input"""
	def __init__(self, *multOf):
		super(SumOfMultiples, self).__init__()
		if not multOf:
			self.multOf = [3,5]
		else:
			self.multOf = multOf
	def to(self, n):
		multiples = []
		for m in self.multOf:
			multiples.extend([x for x in range(n) if x % m == 0])
		return sum(list(set(multiples))) #remove duplicates and sum
