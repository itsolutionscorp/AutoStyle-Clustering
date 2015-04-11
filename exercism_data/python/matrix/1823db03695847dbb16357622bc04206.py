class Matrix(object):
	def __init__(self, raw_input):
		"""We assume raw_index has a matrix of at least dimension 1x1."""
		self.rows = map(lambda s: map(int, s.split()), raw_input.split('\n'))
		self.columns = [
			[self.rows[column_index][row_index] for column_index in range(len(self.rows))]
			for row_index in range(len(self.rows[0]))
		]
