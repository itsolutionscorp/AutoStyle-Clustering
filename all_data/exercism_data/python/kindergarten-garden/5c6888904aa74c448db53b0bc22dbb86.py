# garden.py

plantList= { 'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets' }

class Garden:
	def __init__(self, gardenDef,
					students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
										'Ginny', 'Harriet', 'Ileana', 'Joseph',
										'Kincaid', 'Larry']):
		self.gardenDef=gardenDef.split()
		self.studentList=sorted(students)
		
	def plants(self, name):
		i = self.studentList.index(name) * 2
		return [plantList[p] for p in list(self.gardenDef[0][i:i+2] + self.gardenDef[1][i:i+2])]
