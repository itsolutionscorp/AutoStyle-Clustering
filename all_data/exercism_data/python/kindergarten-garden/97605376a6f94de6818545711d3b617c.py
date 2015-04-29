class Garden:

	names = [chr(i) for i in range(65, 77)] # A - L
	full = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

	def plants(self, name):
		index = self.students.index(name[0])
		start, stop = 0+(index*2), 2+(index*2)
		return [self.full[i] for i in self.garden[0][start:stop]] + \
				[self.full[i] for i in self.garden[1][start:stop]]
		
	def __init__(self, garden, students=names):
		self.students = sorted([i[0] for i in students])
		self.garden = list(garden.split('\n'))
