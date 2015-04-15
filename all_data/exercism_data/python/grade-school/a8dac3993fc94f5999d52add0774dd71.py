class School(object):

	def __init__(self, name):

		self.name = name
		self.db = {}

	def add(self, student, grade):

		if not grade in self.db:
			self.db[grade] = set()
		self.db[grade].add(student)

	def grade(self, grade):

		if grade in self.db:
			return self.db[grade]
		else:
			return set()

	def sort(self):

		return [
			(grade, tuple(sorted(list(students))))
			for grade, students in sorted(self.db.items())
		]
