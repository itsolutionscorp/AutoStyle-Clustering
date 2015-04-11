class Luhn(object):

	def __init__(self, n):
		self.n = n
		self._digits = [int(x) for x in str(n)]

	def addends(self):
		terms = []
		for i, x in enumerate(reversed(self._digits)):
			if i % 2 == 0:
				terms.append(x)
			elif 2 * x > 10:
				terms.append(2 * x - 9)
			else:
				terms.append(2 * x)
		return list(reversed(terms))

	def checksum(self):
		return sum(self.addends()) % 10

	def is_valid(self):
		return self.checksum() == 0

	# using class/static methods isn't necessary here IMO, but ok
	@classmethod
	def create(cls, n):
		instance = cls(n * 10)
		chk = instance.checksum()
		return 10 * n + (10 - chk) % 10
