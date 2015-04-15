allergies =  {
		128	: 'cats',
		64	: 'pollen',
		32	: 'chocolate',
		16	: 'tomatoes',
		8	: 'strawberries',
		4	: 'shellfish',
		2	: 'peanuts',
		1	: 'eggs'
	}

class Allergies:
	def __init__(self, amount):
		self.list = []
		for k in reversed(sorted(allergies)):
			if k <= amount:
				amount -= k
				self.list.append(allergies[k])

			while k <= amount:
				amount -= k
		print self.list

	def is_allergic_to(self, item):
		if item in self.list:
			return True
		return False

t = Allergies(257)
print t.is_allergic_to('cats')
