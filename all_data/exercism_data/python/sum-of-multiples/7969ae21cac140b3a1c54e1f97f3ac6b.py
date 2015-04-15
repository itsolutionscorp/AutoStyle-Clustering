class SumOfMultiples(object):

	def __init__(self, *n):
		if n == ():
			self.n = (3, 5)
		else:
			self.n = n
	
	def to(self, limit):
		result = []

		for i in self.n:
			for j in range(0, limit, i):
				if j not in result:
					result.append(j)

		return sum(result)
