class Matrix:
	def __init__(self, plain):
		self.rows = []
		self.columns = []
		row = 0
		col = 0
		for r in plain.split('\n'):
			self.rows.append([])
			col = 0
			for cell in r.split():
				if row == 0:
					self.columns.append([])
				self.rows[row].append(int(cell))
				self.columns[col].append(int(cell))
				col += 1
			row += 1
