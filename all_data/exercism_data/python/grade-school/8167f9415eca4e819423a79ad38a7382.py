#/usr/bin/env python
class School:
	def __init__(self, name):
		self.name = name
		self.db = {}
	def add(self, stud, grade):
		if not self.db.get(grade, False): self.db[grade] = set()
		self.db[grade].add(stud)
	def grade(self,grad):
		return self.db.get(grad, set())
	def sort(self):
		result = []
		for sl, ml in sorted(self.db.iteritems()):
			result.append( (sl, tuple(sorted(ml))) )
		return result
