class School:
	def __init__(self, s):
		self.schoolname = s
		self.db = {}
		
	def add(self, st, gr):
		self.db.setdefault(gr, set()).add(st)
		
	def grade(self, gr):
		return self.db.get(gr, set())
	
	def sort(self):
		return {gr:tuple(sorted(sts)) for gr,sts in sorted(self.db.iteritems())}
