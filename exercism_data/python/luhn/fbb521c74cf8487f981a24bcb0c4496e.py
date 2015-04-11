class Luhn(object):
	def __init__(self, code):
		self.code = str(code)

	def addends(self):
		result = [] 
		pos = 1
		for n in reversed(self.code):
			temp = int(n) * 2
			if pos % 2 == 0:
				if temp >= 10:
					result.insert(0, temp - 9)
				else:
					result.insert(0, temp)
			else:
				result.insert(0, int(n))
			pos += 1
		return result

	def checksum(self):
		return sum(self.addends()) % 10

	def is_valid(self):
		if self.checksum() == 0:
			return True
		return False

	@staticmethod
	def create(code):
		for n in range(10):
			t = int(str(code) + str(n))
			if Luhn(t).is_valid():
					return int(t)
