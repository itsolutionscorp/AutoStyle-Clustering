class School(object):
	
	def __init__(self,school_name):
		self.school_name = school_name
		self.db = {}

	def add(self, student_name, student_grade):
		if student_grade in self.db:
			self.db[student_grade].add(student_name)
		else:
			self.db[student_grade] = {student_name}

	def grade(self, student_grade):
		if student_grade in self.db:
			return self.db[student_grade]
		else:
			return set()

	def sort(self):
		return {grade: tuple([student for student in self.db[grade]]) for grade in self.db}
