
class SumOfMultiples(object):
	def __init__(self, *entries):
		self.entries = entries
		self.entryList = map((lambda x: int(x)), entries)

	def to(self, number):
		multiples = []
		if len(self.entryList) == 0:
			self.entryList = [3, 5]
		for entry in self.entryList:
			for item in range(1, number):
				if item % entry == 0:
					multiples.append(item)
		return sum(multiples)
	
