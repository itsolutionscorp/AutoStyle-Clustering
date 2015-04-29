class School(object):
	
	def __init__(self,name):
		self.name = name
		self.__db = {}
		
	def add(self,name,grade):
		if not grade in self.__db:
			self.__db[grade]={name}
		else:
			self.__db[grade].add(name)
			
	def grade(self,year):
		if not year in self.__db:
			return set()
		else:
			return self.__db[year]
			
	def sort(self):
		out = {}
		for year in sorted(self.__db):
			out[year] = tuple(sorted(self.__db[year]))
		return out
		
	@property
	def db(self):
		return self.__db
