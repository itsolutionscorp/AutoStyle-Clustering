class School:
	def __init__(self, name):
		self.name = name
		self.db = {}

	def add(self, name, grade):
		try:	
			self.db[grade].add(name)
		except KeyError:
			self.db[grade] = set([name])

	def grade(self, grade):
		try:
			return self.db[grade]
		except KeyError:
			return set()

	def sort(self):
		return {grade:tuple(sorted(students)) for (grade, students) in self.db.items()}
