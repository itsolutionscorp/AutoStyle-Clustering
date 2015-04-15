# school.py

class School:
	def __init__(self, name):
		self.name = name
		self.db = {}
		
	def grade(self, n):
		if n in self.db.keys():
			return self.db[n]
		else:
			return set()
		
		
	def add(self, name, grade):
		if grade in self.db.keys():
			self.db[grade].add(name)
		else:
			self.db[grade] = {name}
		
	def sort(self):
		sorted_students={}
		for grade in self.db.keys():
			sorted_students[grade] = tuple(sorted(self.db[grade]))
		return sorted_students
	
