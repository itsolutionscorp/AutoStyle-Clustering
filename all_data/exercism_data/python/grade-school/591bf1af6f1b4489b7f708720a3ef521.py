import collections

class School():
	
	def __init__(self, school):
		self.school = school
		self.db = collections.defaultdict(set)

	def add(self, student_name, grade):
		self.db[grade].add(student_name)

	def grade(self, grade):
		return self.db[grade]

	def sort(self):
		return {grade: tuple(sorted(student_names)) for grade, student_names in self.db.items()}

	def __str__(self):
		to_string = ''
		for keys, values in self.db.iteritems():
			to_string += '%s : %s\n' %(keys, values)

		return to_string
