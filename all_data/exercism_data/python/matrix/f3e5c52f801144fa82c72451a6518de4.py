class Matrix(object):
	def __init__(self, m):
		if m:
			self.rows = [[int(x) for x in r.split()] for r in m.split('\n')]
			self.columns = [list(l) for l in zip(*self.rows)]
		else:
			self.rows = None
			self.columns = None
