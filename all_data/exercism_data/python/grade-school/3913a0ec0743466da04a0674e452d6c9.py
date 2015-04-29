class School(object):

	__slots__ = ['name', '_School__db']

	def __init__(self, name):
		self.__db = dict()
		self.name = name

	def add(self, student, grade):
		self.__db[grade] = self.grade(grade).union(set([student]))

	def grade(self, grade):
		return self.__db.get(grade, set())

	def sort(self):
		return [(grade, tuple(sorted(self.__db[grade])))
					for grade in sorted(self.__db.iterkeys())]

	def __getattr__(self, name):
		if name == 'db':
			# Return a copy of db so that our internal structure is less likely
			# to be corrupted by outsiders.
			# No need for deepcopy since sets are immutable.
			return self.__db.copy()
