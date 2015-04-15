class Garden(object):

	__plant_names = {'G': 'Grass', 'C': 'Clover', 'V': 'Violets', 'R': 'Radishes'}

	def __init__(self, graph, students=('Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry').split()):
		self.rows = graph.split()
		self.students = sorted(students)

	def plants(self, student):
		startSpot = self.students.index(student) * 2
		spot = slice(startSpot, startSpot + 2)
		return [self.__plant_names[plant]
				for plant in (self.rows[0][spot] +
			       			 self.rows[1][spot])]
