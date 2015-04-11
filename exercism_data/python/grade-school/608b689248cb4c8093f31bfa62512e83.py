
class School(object):
	
	def __init__(self, name):
		self.name = name
		self.db = {}

	def add(self, student, level):
		if level in self.db:
			self.db[level].add(student)
		else:
			self.db[level] = {student}
			
	def grade(self, level):
		if level in self.db:
			return self.db[level]
		else:
			return set()
	
	def sort(self):
		sorted_db = {}
		for gl in self.db:
			gradelevel = self.db[gl]
			gradelevel = sorted(gradelevel)
			sorted_db[gl] = tuple(gradelevel)
		return sorted_db

		
		
