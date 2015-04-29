class NotAllowedError(Exception):
	pass

class School(object):
	"""School object to hold information about student and grades"""
	def __init__(self, name):
		self.name = name
		self.__db = dict()

	def add(self, student, _grade):
		"""Add student to grade"""
		if _grade not in self.__db.keys():
			self.__db[_grade] = set()
		self.db[_grade].add(student)

	def grade(self, _grade):
		"""Set of students of given grade"""
		return self.__db[_grade] if _grade in self.__db.keys() else set()

	def sort(self):
		"""Return whole database sorted by grade and alphebtically"""
		return ((key,tuple(value)) for key,value in sorted(self.__db.items()))

	@property
	def db(self):
		return self.__db

	@db.setter
	def db(self, _db):
		raise NotAllowedError('It is forbidden to change db directly')
