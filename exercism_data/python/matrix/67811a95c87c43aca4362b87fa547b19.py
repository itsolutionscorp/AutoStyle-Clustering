class Matrix:
	
	def __init__(self, mat):
		mat = mat.split("\n");
		for r in range(0, len(mat)):
			mat[r] = mat[r].split()

		self.rows = [[] for r in range(0,len(mat)) ]
		self.columns = [[] for c in range(0, len(mat[0])) ]

		for r in range(0, len(mat)) :
			for c in range(0, len(mat[r])):
				self.rows[r].append( int(mat[r][c]) )
				self.columns[c].append( int(mat[r][c]) )
