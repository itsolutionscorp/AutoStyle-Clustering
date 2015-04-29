class SumOfMultiples(object):
	"""Can find the sum of all the multiples of its arguments."""
	def __init__(self, *args):
		self.multipleof =  []
		if len(args):
			for i in range(0, len(args)):
				self.multipleof.append(args[i])
		else:
			self.multipleof.append(3)
			self.multipleof.append(5)

	def to(self, n):
		return sum(x for x in range(0, n) if self._ismultiple(x) == True)

	def _ismultiple(self, n):
		for i in range(0, len(self.multipleof)):
			if n % self.multipleof[i] == 0:
				return True
		return False
