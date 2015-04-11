class Matrix(object):
	def __init__(self, elts_string):
		row_strs = [s.strip() for s in elts_string.split('\n')]
		self.rows = [ [ int(elt) for elt in row_str.split(' ')] for row_str in row_strs ]
		num_columns = len(self.rows[0])
		num_rows = len(self.rows)
		self.columns = []
		for j in xrange(0, num_columns):
			column = []
			for i in xrange(0, num_rows):
				column.append(self.rows[i][j])
			self.columns.append(column)
