	
class School:
	def __init__(self,name):
		self.name = name
		self.db={}

	def add(self,student, grad):
		if grad not in self.db:
			self.db[grad]={student}
		else:
			self.db[grad].add(student)
			
	def grade(self,grad):
		if grad not in self.db:
			return set()
		else:
			return self.db[grad]
		
	def sort(self): #where I fail
		newdict={} #new dictionary 
		for i in self.db: 
			newdict[i]=tuple(s for s in self.db[i])
		return newdict
