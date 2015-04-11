from collections import defaultdict

class School(object):
	def __init__(self, name):
		self.name = name
		self.db = defaultdict(lambda: set())

	def add(self, name, n):
		self.db[n].add(name)

	def grade(self, n):
		return self.db[n]

	def sort(self):
		return {gr: tuple(sorted(sts)) for gr,sts in sorted(self.db.iteritems())}
