class Matrix():
	def __init__(self,text):
		mat = [i.split(" ") for i in text.split("\n")]
		n = len(mat)
		m = len(mat[0])
		self.rows = [[int(mat[i][j]) for j in range(m)] for i in range(n)]
		self.columns = [[int(mat[i][j]) for i in range(n)] for j in range(m)]
