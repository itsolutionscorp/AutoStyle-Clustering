from collections import defaultdict

class School(object):

	def __init__(self, student):
		self.student = student 
		self.db = defaultdict(set)
	
	def add(self, name, grade):
		self.db[grade].add(name)
		

	def grade(self, number):
		return self.db[number]

	def sort(self):
		return {
    	grade: tuple(sorted(students))
    		for grade, students in self.db.items()
    	}
