class Matrix(object):
  def __init__(self,matrix):
    if (len(matrix)!=0):
      self.get_matrix(matrix)
      self.get_rows()
      self.get_columns()

  def get_matrix(self,matrix):
    self.matrix = []
    for row in  matrix.split('\n'):
      row_int = []
      for el in row.split():
	row_int.append(int(el))
      self.matrix.append(row_int)

  def get_rows(self):
    self.rows = []
    for row in self.matrix:
      self.rows.append(row)

  def get_columns(self):
    self.columns = []
    for i in range(len(self.matrix[0])):
      self.columns.append([])
    for row in self.matrix:
      col = 0
      for el in row:
	self.columns[col].append(el)
	col += 1
