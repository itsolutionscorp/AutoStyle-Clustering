from copy import deepcopy

# represents a single school
class School(object):
	def __init__(self,name):
		self.name=name
		self._db={}

	# adds student name to grade
	def add(self,name,grade):
		if grade not in self._db.keys():
			self._db[grade]=set()
		self._db[grade].add(name)

	# returns a set of all students in grade
	def grade(self,grade):
		return deepcopy(self._db.get(grade,set()))

	# returns a dictionary of sorted tuples of students names in each grade
	def sort(self):
		return { n:tuple(sorted(self._db[n])) for n in self._db.keys() }

	# the school's database
	@property
	def db(self):
		return deepcopy(self._db)
