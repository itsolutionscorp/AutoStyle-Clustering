class Garden:
	
	def __init__(self, garden_diagram, students = "Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry".split() ):
		self.garden_diagram = garden_diagram
		self.students = sorted(students)
		
	def plants(self, student):
				
		plant_names = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}

		firstrow, secondrow = self.garden_diagram.split()
		
		plant_index = 2*self.students.index(student)
		
		student_plants = []
		
		student_plants.append(plant_names[firstrow[plant_index]])
		student_plants.append(plant_names[firstrow[plant_index+1]])
		student_plants.append(plant_names[secondrow[plant_index]])
		student_plants.append(plant_names[secondrow[plant_index+1]])
		
		return student_plants
			
