class Garden(object):

	abbrevs = {'R': "Radishes", 'G': "Grass", 'C': "Clover", 'V': "Violets"}

	def __init__(self, str, students=None):
		self.rows = str.split('\n')
		self.students = students or ["Alice", "Bob", "Charlie", "David",
									 "Eve", "Fred", "Ginny", "Harriet",
									 "Ileana", "Joseph", "Kincaid", "Larry"]
		self.students.sort()

	def _name_to_col(self, name):
		return 2 * self.students.index(name)

	def plants(self, name):
		col = self._name_to_col(name)
		letters = [self.rows[i][j] for i, j in ((0, col), (0, col + 1), (1, col), (1, col + 1))]
		return [self.abbrevs[x] for x in letters]
