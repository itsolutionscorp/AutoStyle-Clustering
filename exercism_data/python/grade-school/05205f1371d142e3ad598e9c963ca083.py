class School:

	def sort(self):
		result = []
		for key in sorted(self.db):
			x = key, tuple(self.db[key])
			result.append(x)
		return result

	def grade(self, grade):
		return self.db[grade] if grade in self.db else set()

	def add(self, name, grade):
		if grade not in self.db:
			self.db[grade] = set([name])
		else:
			self.db[grade].add(name)

	def __init__(self, name):
		self.name = name
		self.db = {}
