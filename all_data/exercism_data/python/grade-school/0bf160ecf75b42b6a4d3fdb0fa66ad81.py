class School:
	def __init__(self,name):
		self.school= name
		self.db = {}
	def add(self,name,Grade):
		grades = self.grade(Grade)
		grades.add(name)
		self.db[Grade] = grades
	def grade(self,Grade):
		return self.db.get(Grade,set())
	def sort(self):
		dbSorted = {}
		for i in sorted(self.db.keys()):
			dbSorted[i] = tuple(self.db[i])
		return dbSorted
