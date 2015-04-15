class Matrix(object):
	def __init__(self, raw_input):
		self.rows = [map(int, row.split()) for row in raw_input.split('\n')]
		self.columns = [list(column) for column in zip(*self.rows)]
