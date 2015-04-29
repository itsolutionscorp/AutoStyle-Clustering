class Matrix(object):
	def __init__(self, mat_str):
		if mat_str:
			self.rows = [[int(x) for x in r.split(' ')] for r.strip() in mat_str.split('\n')]
			self.columns = [[row[col] for row in self.rows]
							for col in range(len(self.rows[0]))]
		else:
			self.rows = None
			self.columns = None
