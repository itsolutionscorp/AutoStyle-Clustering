class School:

	def __init__(self, name):
		self.name = name
		self.db = {}

	def add(self, name, grade):
		if grade in self.db:
			self.db[grade].add(name)
		else:
			self.db[grade] = {name}
			
	def grade(self, number):
		if number in self.db:
			return self.db[number]
		else:
			return set()

	def sort(self):
		newdict = {}
		for key in self.db:
			newdict[key] = tuple(self.db[key])
		return newdict
