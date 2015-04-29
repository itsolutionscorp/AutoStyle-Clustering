class Matrix(object):

	def __init__(self, matrix_str):
		row_strs = matrix_str.split("\n")

		self.row_count = len(row_strs)
		self.rows = [
			map(int, row_str.split())
			for row_str in row_strs
		]

		self.col_count = len(self.rows[0])
		self.columns = [
			[row[col_idx] for row in self.rows]
			for col_idx in range(self.col_count)
		]
