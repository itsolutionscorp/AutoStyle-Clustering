class Matrix(object):
	def __init__(self, num_string):
		self.rows = self._parse_row_data(num_string)
		self.columns = self._parse_col_data()

	def _parse_row_data(self, num_string):
		row_data = []
		for row in num_string.split("\n"):
			row_data.append(map(int, row.split()))

		return row_data

	def _parse_col_data(self):
		column_data = []
		for col_count in xrange(0,len(self.rows[0])):
			column_data.append([])
			for col_elem in xrange(0,len(self.rows)):
				column_data[col_count].append(self.rows[col_elem][col_count])

		return column_data
