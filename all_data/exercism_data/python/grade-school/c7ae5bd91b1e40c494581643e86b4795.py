class School(object):
	"""docstring for ClassName"""
	def __init__(self, school_name):
		self.school = school_name
		self.db = {}
	def add(self,name, grade):
		if grade in self.db:
			self.db[grade].add(name)
		else:
			self.db[grade] = set()
			self.db[grade].add(name)
	def grade(self, grade_number):
		if grade_number in self.db:
			return self.db[grade_number]
		else:
			return set()	
	def sort(self):
		sort= {}
		for grade in sorted(self.db.keys()):
			sort[grade] = tuple(sorted(self.db[grade]))
		return sort
