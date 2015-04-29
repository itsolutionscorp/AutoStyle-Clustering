''' school.py
	created Oct 9 2014
	by @jestuber'''

class School(object):
	"""docstring for School"""
	def __init__(self, schoolName):
		super(School, self).__init__()
		self.schoolName = schoolName
		self._db = {}
	def add(self, name, grade):
		self._db.setdefault(grade, set()).add(name)

	@property
	def db(self):
		return {grade: students.copy() for grade, students in self._db.items()}

	def grade(self,grade):
		return self._db.get(grade, set()).copy()

	def sort(self):
		return {grade: tuple(sorted(students.copy())) for grade, students in self._db.items()}
