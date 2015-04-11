class Matrix:

	def __init__(self, matrix):
		rows = [row.split(" ") for row in matrix.split("\n")]
		self.rows = []
		for row in rows:
			self.rows.append(list(map(int, row)))
		self.columns = []
		for i in range(len(self.rows[0])):
			self.columns.append([row[i] for row in self.rows])
