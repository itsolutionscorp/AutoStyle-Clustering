class Matrix:
	def __init__(self, data):
		self.rows = [map(int, line.split()) for line in data.split("\n")]
		self.columns = map(list, zip(*self.rows))
