class Allergies(object):
	values = [
		('eggs', 1),
		('peanuts', 2),
		('shellfish', 4),
		('strawberries', 8),
		('tomatoes', 16),
		('chocolate', 32),
		('pollen', 64),
		('cats', 128)
	]

	def __init__(self, score):
		self.list = [name for (name, value) in self.values if score & value]

	def is_allergic_to(self, name):
		return name in self.list
