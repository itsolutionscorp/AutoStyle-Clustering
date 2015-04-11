class Garden:
	
	def __init__(self, plants, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
		self.students = sorted(students)
		self.pNames = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}
		self.sCup = {}
		for x in self.students:
			self.sCup[x] = []
		self.splitCup(plants.split('\n'))
	
	def plants(self, s):
		return self.sCup[s]
		
	def splitCup(self,cups):
		for cup in cups:
			for x in range(0,len(cup)):
				self.sCup[self.students[int(round(x/2))]].append(self.pNames[cup[x]])
