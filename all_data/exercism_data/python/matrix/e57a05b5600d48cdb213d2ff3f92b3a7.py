class Matrix(object):

	def __init__(self, matrix_str):
		self.rows = [
			[int(n) for n in row_str.split()]
			for row_str in matrix_str.splitlines()
		]
		self.columns = [list(col) for col in zip(*self.rows)]
