class Matrix(object):
	def __init__(self, matrix_string):
		self.rows = [map(int, row_string.split(' ')) for row_string in matrix_string.split("\n")]
		self.columns = map(list, zip(*self.rows))
