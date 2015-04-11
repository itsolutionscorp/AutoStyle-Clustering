''' school.py
	created Oct 9 2014
	by @jestuber'''

class School(object):
	"""docstring for School"""
	def __init__(self, schoolName):
		super(School, self).__init__()
		self.schoolName = schoolName
		self.db = {}
	def add(self, name, grade):
		if grade not in self.db:
			self.db[grade] = set([])
		self.db[grade].add(name)

	def db(self):
		return self.db

	def grade(self,grade):
		if grade not in self.db:
			return set([])
		return self.db[grade]

	def sort(self):
		dbSort = {}
		for grade in self.db:
			dbSort[grade] = tuple(sorted(self.db[grade]))
		return dbSort
