class School(object):

	def __init__(self, name):
		self.name = name
		self.db = {}

	def add(self, name, grade):
		if grade not in self.db:
			self.db[grade] = set()
		self.db[grade].add(name)

	def grade(self, num):
		if num not in self.db:
			return set()
		return self.db[num]

	def sort(self):
		return {x:tuple(self.db[x]) for x in sorted(self.db)}
