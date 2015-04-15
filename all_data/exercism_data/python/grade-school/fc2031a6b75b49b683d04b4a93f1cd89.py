import sqlite3 as lite

class School():
	def __init__(self, name):
		self.name = name
		self.db = dict()

	def add(self, name, grade):
		if self.db.has_key(grade):
			self.db[grade].add(name)
		else:
			self.db[grade] = {name}
	

	def grade(self, grade):
		if not self.db.has_key(grade):
			self.db[grade] = set()
		return self.db[grade]
	def sort(self):
		return tuple((grade, tuple(self.db[grade])) for grade in sorted(tuple(self.db)))
