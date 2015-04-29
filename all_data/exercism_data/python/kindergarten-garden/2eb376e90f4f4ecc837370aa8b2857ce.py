from itertools import chain

class Garden:
	"""Class to keep track of child-to-plants assignments
	"""

	plants_dict = {
		'G': 'Grass',
		'C': 'Clover',
		'R': 'Radishes',
		'V': 'Violets',
	}

	default_students = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']
	
	def __init__(self, plant_list, students=default_students):
		# Split given plant string into list with 2 substrings ex. "VVCCGG\nVVCCGG" -> ["VVCCGG","VVCCGG"]
		self.plant_list=plant_list.split()
		# Assing and sort students
		self.students=sorted(students)

	def plants(self, student):
		"""Returns a list of plants that are assigned to student

		Keyword arguments:
		student -- string with student name
		"""
		# Calculate plant index based on student index in sorted list
		idx = self.students.index(student)*2
		# For each row choose 2 plants that are assigned to a given student, then iterate over them in chain do dictionary check
		return [self.plants_dict[i] for i in chain.from_iterable(x[idx:idx+2] for x in self.plant_list)]
		# Alternative approach - construct one string
		#return [self.plants_dict[i] for i in ("".join(x[idx:idx+2] for x in self.plant_list))]
