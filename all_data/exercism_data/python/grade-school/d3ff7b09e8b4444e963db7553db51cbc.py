'''
	This class represents a school db
'''
class School(object):
	'''
		Initalize the school db and set the school name
	'''
	def __init__(self, school_name):
		self.school_name = school_name
		self.db = dict()

	'''
		Add a student to a given grade
	'''
	def add(self, student, grade):
		if grade not in self.db:
			self.db[grade] = {(student)}
		else:
			self.db[grade].add(student)

	'''
		Given a grade, return a list of all the students in that grade
	'''
	def grade(self, grade_num):
		if grade_num not in self.db:
			return set()
		else:
			return self.db[grade_num]

	'''
		Return a sorted map. The keys are sorted according to ascending grade levels
		The values are sorted alphabetically
	'''
	def sort(self):
		return {grade: tuple(sorted(student_list)) for grade, student_list in self.db.items()}
