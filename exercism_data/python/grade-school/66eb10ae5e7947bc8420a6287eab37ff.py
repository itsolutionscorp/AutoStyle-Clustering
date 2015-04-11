class School:
	def __init__(self, s):
		self.db = {}
		
	def add(self, st, gr):
		if gr not in self.db:
			self.db[gr] = set([st])
		else:
			self.db[gr].add(st)
		
	def grade(self, gr):
		if gr in self.db:
			return self.db[gr]
		else:
			return set()
	
	def sort(self):
		return {gr:tuple(self.db[gr]) for gr in self.db.keys()}
