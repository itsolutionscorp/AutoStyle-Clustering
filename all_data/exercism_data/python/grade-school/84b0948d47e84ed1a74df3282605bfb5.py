class School(object):

	def __init__(self, schoolname):
		self._db = {}
		self.schoolname = schoolname

	def add(self, name, grade):
		if grade not in self._db.keys():
			self._db[grade] = set([name])
		else:
			self._db[grade].add(name)

	def grade(self, grade):
                if grade not in self._db:
                        return set()
		return self._db[grade]

	def sort(self):
		sorted_school = {}
		for grade in self._db:
			sorted_school[grade] = tuple(sorted(self._db[grade]))
		return sorted_school

	@property
	def db(self):
		return self._db
