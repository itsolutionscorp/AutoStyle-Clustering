#students = {'Alice': 0, 'Bob':1, 'Charlie':2, 'David':3, 'Eve':4, 'Fred':5, 'Ginny':6, 'Harriet':7, 'Ileana':8, 'Joseph':9, 'Kincaid':10, 'Larry':11}
plants = {'G':'Grass', 'V': 'Violets', 'R': 'Radishes', 'C': 'Clover'}

class Garden():
	
	def __init__(self, garden, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
		self.garden_rows = []
		self.students = sorted(students)
		for row in garden.splitlines():
			num_plants = 2
			self.garden_rows.append([row[i:i+num_plants] for i in range(0, len(row), num_plants)])

	def a(self):
		print self.garden_rows

	def plants(self, student):
		student_plants = []
		for student_row in self.garden_rows:
			plants_row = student_row[self.students.index(student)]
			for i in range(0,2):
				student_plants.append(plants.get(plants_row[i]))

		return student_plants

g = Garden('GVVC\nVRCG')
print g.plants('Alice')
