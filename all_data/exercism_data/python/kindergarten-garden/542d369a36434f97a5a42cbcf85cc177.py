class Garden(object):
	def __init__(self, garden, students=None):

		self.responsibilities = {}

		if students is None:
			students = 'Alice Bob Charlie David Eve Fred Ginny Harriet Ileana Joseph Kincaid Larry'.split(' ')
		students = sorted(students)

		garden = garden.split("\n")
		for child_index, child in enumerate(students):
			self.responsibilities[child] = []
			for row in garden:
				for code in row[child_index*2:child_index*2+2]:
					self.responsibilities[child].append(_plant_name(code))
			child_index += 1

	def plants(self, child):
		return self.responsibilities.get(child, [])


plant_names = {
	'V': 'Violets',
	'R': 'Radishes',
	'C': 'Clover',
	'G': 'Grass'
}
def _plant_name(code):
	return plant_names.get(code, 'Weed')
