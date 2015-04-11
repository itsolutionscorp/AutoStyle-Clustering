class School:

	def __init__(self,name):
		self.name = name
		self.db = {}

	def add(self, student, grade):
		try:
			self.db[grade].add(student)
		except KeyError:
			self.db[grade] = {student}

	def grade(self, grade_num):
		try:
			return self.db[grade_num]
		except KeyError:
			return set()

	def sort(self):
		sort_db = {}
		for i in self.db.iterkeys():
			sort_db[i] = tuple(sorted(self.db[i]))
		return sort_db
