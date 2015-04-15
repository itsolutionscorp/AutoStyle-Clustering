plant_dict = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

class Garden():
	
	PLANT_QTY = 4

	def __init__(self, garden,
		students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
		self.garden = garden.split('\n')
		self.students = students
		self.students.sort()

	def plants(self, student):
		i = self.students.index(student)
		student_plants = []
		for line in self.garden:
			for j in range(Garden.PLANT_QTY/len(self.garden)):
				student_plants.append(plant_dict[line[i*2 + j]])
		return student_plants
