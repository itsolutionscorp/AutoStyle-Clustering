class SumOfMultiples(object):
	def __init__(self, *args):
		if args:
			self.mults = [x for x in args]
		else:
			self.mults = [3,5]
			
	def to(self, number):
		all_mults = []
		for i in range(1, number):
			for m in self.mults:
				if i not in all_mults and i%m == 0:
					all_mults.append(i)
		return sum(all_mults)

test = SumOfMultiples()
print test.to(10)
