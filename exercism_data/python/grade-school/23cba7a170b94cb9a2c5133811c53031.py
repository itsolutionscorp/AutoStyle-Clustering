
class School:
	def __init__(self, name):
		self.name = name
		self.db = {}
	
	def add(self, student, grade):
		if grade in self.db.keys():
			self.db[grade].add(student)
		else:
			self.db[grade] = {student}
	
	def grade(self, year):
		try:
			return self.db[year]
		except KeyError:
			return set()
	
	def sort(self):
		sorteddb = {i:tuple(self.db[i]) for i in self.db}
		return sorteddb
