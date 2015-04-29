class School(object):
	
	def __init__(self,name):
		self.name = name
		self.db = {}
		
	def add(self,name,grade):
		if not grade in self.db:
			self.db[grade]={name}
		else:
			self.db[grade].add(name)
			
	def grade(self,year):
		if not year in self.db:
			return set()
		else:
			return self.db[year]
			
	def sort(self):
		out = {}
		for year in sorted(self.db):
			out[year] = tuple(sorted(self.db[year]))
		return out
