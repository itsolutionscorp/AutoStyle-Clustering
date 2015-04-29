class Matrix(object):
	def __init__(self, raw_text):
		self.rows = [list(map(int, row.split())) for row in raw_text.split('\n')]
		self.columns = [list(column) for column in zip(*self.rows)]
