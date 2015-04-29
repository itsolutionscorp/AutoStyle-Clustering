class Garden():
	def __init__(self, garden, students = None):
		self.rows = [list(row) for row in garden.lower().split()]
		if students is None:
			self.students = ['Alice', 'Bob', 'Charlie', 'David',
				'Eve', 'Fred', 'Ginny', 'Harriet',
				'Ileana', 'Joseph', 'Kincaid', 'Larry']
		else:
			self.students = sorted(students)
		self.flower_dict = {k:v for k,v in zip('gcrv',
			['Grass','Clover','Radishes','Violets'])}
	def plants(self, student):
		index = self.students.index(student)*2
		plant = ''.join([''.join(x[index:index+2]) for x in self.rows])
		return [self.flower_dict[p] for p in plant]
