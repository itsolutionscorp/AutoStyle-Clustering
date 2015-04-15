class School():
	
	def __init__(self, school):
		self.school = school
		self.db = {}

	def add(self, student_name, grade):
		if grade in self.db:
			self.db.update({grade : [self.db[grade], student_name]})
		else:
			self.db[grade] = [student_name]

	def grade(self, grade):
		return self.db.get(grade)

	def sort(self):
		students = [(key, value) for key, value in self.db.iteritems()]
		return students.sort()

	def __str__(self):
		to_string = ''
		for keys, values in self.db.iteritems():
			to_string += '%s : %s\n' %(keys, values)

		return to_string

school = School('Sunny Hills')
school.add('jacob', 4)
print school
school.add('blake', 2)
print school
school.add('luke', 7)
print school
school.add('tyler', 7)
print school
