class School(object):
	def __init__(self, school_name):
		self.school_name = school_name
		self.db = dict()

	def add(self, student, grade):
		if grade not in self.db:
			self.db[grade] = {(student)}
		else:
			self.db[grade].add(student)

	def grade(self, grade_num):
		if grade_num not in self.db:
			return set()
		else:
			return self.db[grade_num]

	def sort(self):
		return self.db
