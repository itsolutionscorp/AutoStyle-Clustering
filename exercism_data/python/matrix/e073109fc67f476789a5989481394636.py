class Matrix(object):

  def __init__(self, matrix):
    self.rows = [ [int(item) for item in row.split()] for row in matrix.split('\n') ]
    self.columns = [ list(col) for col in zip(*self.rows) ]
