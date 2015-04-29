class Allergies:
	
	_score = 0
	_causes = [
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
		self._score = score
		self._causesDict = dict(self._causes)
		self.list = list(d for (d, c) in self._causes if c & score != 0)
	
	def is_allergic_to(self, cause):
		return self._causesDict[cause] & self._score != 0
