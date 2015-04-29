class Garden:
	STUDENTS = ['Alice', 'Bob', 'Charlie', 'David', 'Eve', 'Fred',
		'Ginny', 'Harriet', 'Ileana', 'Joseph', 'Kincaid', 'Larry']

	FLOWERS = {'C': 'Clover', 'R': 'Radishes',
		'G': 'Grass', 'V': 'Violets'}

	def __init__(self, garden, students=STUDENTS):
		garden = garden.split()
		flowers = zip(*[iter(garden[0])]*2 + [iter(garden[1])]*2)
		self.pots = dict(zip(sorted(students), flowers))

	def plants(self, who):
		return map(self.FLOWERS.get, self.pots[who])
