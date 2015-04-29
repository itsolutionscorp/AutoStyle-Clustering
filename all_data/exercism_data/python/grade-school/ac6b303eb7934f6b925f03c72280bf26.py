class School():
	def __init__(self, name):
		self.name = name
		self.db = {}

	def add(self, name, grade):
		if grade in self.db:
			self.db[grade].add(name)
		else:
			self.db[grade] = {name}

	def grade(self, grade):
		if grade in self.db:
			return self.db[grade]
		else:
			return set()

	def sort(self):
		return {k:tuple(sorted(self.db[k])) for k in sorted(self.db.keys())}
