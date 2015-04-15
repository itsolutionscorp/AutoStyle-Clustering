class School:
	def __init__(self, name):
		self.db = {}
	
	def add(self, name, grade):
		if grade in self.db:
			self.db[grade] = self.db[grade].union({name})
		else:
			self.db[grade] = {name}
	
	def grade(self,g):
		if g in self.db:
			return self.db[g]
		else :
			return set()
	
	def sort(self):
		return [(k,tuple(v)) for k,v in sorted(self.db.items()) ]
