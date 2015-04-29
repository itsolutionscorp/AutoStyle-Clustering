class Garden:

	def __init__(self, garden, students=None):
		s = students
		self.garden = garden
		if s != None:
			s.sort()
			self.names = [x[0] for x in s] 
		else:
			self.names = [chr(x) for x in range(65, 78, 1)]
		self.p = {
			"C": "Clover",
			"R": "Radishes",
			"G": "Grass",
			"V": "Violets"
		}

	def plants(self, name):
		# select garden section with the string index
		index = self.names.index(name[0]) * 2
		p = [x[index:index+2] for x in self.garden.split("\n")]
		# return the full name of the plant
		return [self.p[x] for x in list(''.join(p))]
