import operator

class School:
	def __init__(self, school_name):
		self.school_name = school_name
		self._db = {}

	def add(self, name, year):
		self.grade(year).add(name)

	def getdb(self):
		return self._db

	def grade(self, year):
		if not year in self._db:
			self._db[year] = set()
		return self._db[year]

	def sort(self):
		return list(map(self.tuple_grade, sorted(self._db.keys())))

	def tuple_grade(self, grade):
		return (grade, tuple(sorted(self.grade(grade))))

	db = property(getdb)
