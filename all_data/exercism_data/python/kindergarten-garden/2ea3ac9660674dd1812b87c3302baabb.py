default_students = ["Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"]
plant_types = {'C': 'Clover', 'G': 'Grass', 'R': 'Radishes', 'V': 'Violets'}

class Garden:
	def __init__(self, pots, students = default_students):
		self.pots = pots.split("\n")
		self.students = sorted(students)

	def plants(self, student_name):
		idx = self.students.index(student_name)
		pot_chars = ''
		for line in self.pots:
			pot_chars += line[(idx * 2):((idx + 1) * 2)]
		return [plant_types[c] for c in pot_chars]
