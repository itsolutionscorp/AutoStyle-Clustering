from collections import defaultdict

class School(object):
	def __init__(self, school_name):
		self.name = school_name
		self.db = defaultdict(set) 
	def add(self, name, mark):
		self.db[mark].add(name) 
	def grade(self, grade):
		return self.db[grade]
	def sort(self):
		return dict((grade, tuple(sorted(students))) for (grade, students) in self.db.items())
