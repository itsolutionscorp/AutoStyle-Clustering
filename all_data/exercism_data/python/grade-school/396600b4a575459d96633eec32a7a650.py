class School(object):

	def __init__(self, school_name):
		self.school_name = school_name
		self.db = {}

	def add(self, name, grade):
		if grade not in self.db:
			self.db[grade] = set()
			self.db[grade].add(name)
		else:
			self.db[grade].add(name)

	def grade(self, grade):
		if grade not in self.db:
			return set()
		else:
			return self.db[grade]

	def sort(self):
		sorted_db = {}
		
		for key in self.db:
			sorted_db[key] = tuple(sorted(self.db[key]))

		return sorted_db
