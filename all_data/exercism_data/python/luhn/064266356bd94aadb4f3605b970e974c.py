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

	# WTF is this a class method?  But OK, here you go:
	@classmethod
	def create(cls, n):
		instance = cls(int(str(n) + '0'))
		chk = instance.checksum()
		if chk == 0:
			return 10 * n
		else:
			return 10 * n + 10 - chk
