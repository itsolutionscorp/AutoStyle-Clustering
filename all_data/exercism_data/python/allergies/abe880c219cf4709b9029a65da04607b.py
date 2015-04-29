class Allergies(object):
	LIST = ['eggs', 'peanuts', 'shellfish', 'strawberries',
			'tomatoes', 'chocolate', 'pollen', 'cats']
	def __init__(self, score):
		self.score = score % 2**len(self.LIST)
		self.list = []
		if score < 0:
			return
		for i, x in enumerate(reversed(bin(self.score)[2:])):
			if x == '1':
				self.list.append(self.LIST[i])

	def is_allergic_to(self, thing):
		return thing in self.list
