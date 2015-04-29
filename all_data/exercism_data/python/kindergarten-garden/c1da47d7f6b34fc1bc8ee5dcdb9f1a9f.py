class Garden(object):
	def __init__(self, diagram, students=['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred', 'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']):
		plants = {'G': 'Grass', 'C': 'Clover', 'R': 'Radishes', 'V': 'Violets'}
		self.plant_owners = dict()
		(row1, row2) = diagram.splitlines()
		i = 0
		for student in sorted(students):
			if i >= len(row1):
				break
			self.plant_owners[student] = [plants[row1[i]], plants[row1[i+1]], plants[row2[i]], plants[row2[i+1]]]
			i += 2

	def plants(self, student):
		return self.plant_owners[student]
