class Matrix(object):
	def __init__(self, string):
		self.rows = []
		for row in string.split("\n"):
			row_int = row.split(" ")
			for pos in range(len(row_int)):
				row_int[pos] = int(row_int[pos])
			self.rows.append(row_int)

		self.columns = []
		for pos in range(len(self.rows) - 1):
			col = []
			for row in self.rows:
				col.append(int(row[pos]))
			self.columns.append(col)
