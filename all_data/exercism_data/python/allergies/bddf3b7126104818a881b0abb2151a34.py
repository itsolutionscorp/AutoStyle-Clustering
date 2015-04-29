allergies = ['eggs', 'peanuts', 'shellfish', 'strawberries', 'tomatoes', 'chocolate', 'pollen', 'cats']
class Allergies():
	"""docstring for Allergies"""
	def __init__(self, value):
		self.list = []
		for a in allergies:
			if value & 1:
				self.list.append(a)
			value = value >> 1

	def is_allergic_to(self, allergie):
		return allergie in self.list
