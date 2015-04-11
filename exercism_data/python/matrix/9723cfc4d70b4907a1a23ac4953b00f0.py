class Matrix(object):
	def __init__(self, mat_str):
		if mat_str:
			_mat = [row.strip().split(' ') for row in mat_str.split('\n')]
			self.rows = [[int(x) for x in row] for row in _mat]
			self.columns = [[row[col] for row in self.rows]
							for col in range(len(self.rows[0]))]
		else:
			self.rows = None
			self.columns = None
