class Garden(object):
	"""docstring for Garden"""
	def __init__(self, plant_distribution, students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split()):
		self.student_list = sorted(students)
		self.shelf_split = plant_distribution.split("\n")
		self.divided_shelf = []
		self.plant_map()

	def plant_map(self):
		for shelf in self.shelf_split:
			self.divided_shelf.append([shelf[i:i + 2] for i in range(0, len(shelf), 2)])

	def plants(self,student):

		student_index = self.student_list.index(student)

		students_plants = ''.join([plants[student_index] for plants in self.divided_shelf])
		
		students_plants = students_plants.replace('G', 'Grass ')
		students_plants = students_plants.replace('C', 'Clover ')
		students_plants = students_plants.replace('R', 'Radishes ')
		students_plants = students_plants.replace('V', 'Violets ')

		return(students_plants.split())
