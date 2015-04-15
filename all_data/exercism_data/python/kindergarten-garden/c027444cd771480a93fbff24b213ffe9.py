class Garden:
	plant_names = {
		'V': 'Violets',
		'R': 'Radishes',
		'C': 'Clover',
		'G': 'Grass',
	}	

	def __init__(self, garden, students=None):
		self.garden = garden.split('\n')
		if students:
			self.students = sorted(students)
		else:
			self.students = None
		
	def plants(self, student):
		if self.students:
			idx = self.students.index(student)
		else:
			idx = ord(student[0]) - ord('A')
		
		idx *= 2
		return [Garden.plant_names[p] for g in self.garden for p in g[idx:idx+2]]
