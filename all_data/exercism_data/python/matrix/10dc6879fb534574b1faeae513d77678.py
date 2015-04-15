class Matrix:
	def __init__(self, s):
		rows = [[int(i) for i in r.split()] for r in s.split("\n")]
		self.columns = [list(l) for l in zip(*rows)]
		self.rows = rows
