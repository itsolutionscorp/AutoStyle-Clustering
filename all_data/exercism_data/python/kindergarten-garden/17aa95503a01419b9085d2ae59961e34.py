plant_dict = {
	'C': 'Clover',
	'G': 'Grass',
	'R': 'Radishes',
	'V': 'Violets'
}

class Garden(object):

	def __init__(self, rows, students=None):
		self.rows = rows
		self.students = students

	def plants(self, student):

		if self.students:
			student_index = sorted(self.students).index(student)
		else:
			student_index = ord(student[0]) - ord('A')
		student_index *= 2

		row1, row2 = self.rows.split('\n')
		plants = (row1[student_index:student_index+2] +
				  row2[student_index:student_index+2])
		return [plant_dict[plant] for plant in plants]
