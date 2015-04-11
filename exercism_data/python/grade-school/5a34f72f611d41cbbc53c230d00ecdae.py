class School:
	'''
	A small archiving program that stores students' names along with the grade that they are in
	'''
	def __init__(self,school_name='Generic School'):
		self.db = {}
	
	def grade(self,gradelevel):
		if gradelevel in self.db:
			return self.db[gradelevel]
		else:
			return set()
	
	def sort(self):
		return {key:tuple(sorted(value)) for key,value in self.db.items()}

	def add(self,student,gradelevel):
		self.db.setdefault(gradelevel,set()).add(student)
		return 


if __name__ == '__main__':
	school = School('Silly')
	school.add("Martin",4)
	school.add("Leslie",3)
	school.add("Nesmith",4)
