from itertools import izip_longest


def chunk(s, size):
	chunks = len(s) // size if len(s) % size == 0 else len(s) // size + 1
	return [s[i*size:i*size+size] for i in range(chunks)]


class Garden(object):
	'''KindergartenGarden'''
	_children = ['Alice', 'Bob', 'Charlie',
			'David', 'Eve', 'Fred',
			'Ginny', 'Harriet', 'Ileana',
			'Joseph', 'Kincaid', 'Larry']
	_plants = {'G': 'Grass', 'C': 'Clover',
			'V': 'Violets', 'R': 'Radishes'}

	def __init__(self, garden, students=_children):
		self._student_list = students
		self.garden = [chunk(row, 2) for row in garden.split('\n')]
		self.make_garden()

	def make_garden(self):
		student_plants = [a + b for a, b in zip(*self.garden)]
		self.students = dict(izip_longest(
				sorted(self._student_list),	student_plants, fillvalue=''))

	def plants(self, student):
		return [self._plants[plant] for plant in self.students[student]]
