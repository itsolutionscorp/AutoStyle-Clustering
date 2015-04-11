from collections import defaultdict, OrderedDict

class School:
	
	def __init__(self, name):
		self.db = defaultdict(set)

	def add(self, who, grade):
		self.db[grade].add(who)
	
	def sort(self):
		return dict([(grade,tuple(who))
			for (grade,who) in self.db.iteritems()])
	
	def grade(self, grade):
		return self.db[grade]
