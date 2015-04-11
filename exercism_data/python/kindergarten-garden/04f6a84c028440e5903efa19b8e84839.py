class Garden:

	def plants(self, name):

		firstLetter = name[0]
		index = self.students.index(name) if self.students \
			else self.names.index(firstLetter)

		start, stop = 0+(index*2), 2+(index*2)
		row1 = [self.mapping[i] for i in self.garden[0][start:stop]]
		row2 = [self.mapping[i] for i in self.garden[1][start:stop]]
		return row1 + row2
		
	def __init__(self, garden, students=[]):
		self.names = [chr(i) for i in range(65, 77)] # A - L
		self.mapping = {'G': 'Grass', 'C': 'Clover', 
						'R': 'Radishes', 'V': 'Violets'}
		self.students = sorted(students)
		self.garden = list(garden.split('\n'))
