class Matrix:
  def __init__(self, init_string):
    self.rows = self.parseToMatrix(init_string)
    self.columns = self.as_columns(self.rows)

  def parseToMatrix(self, string):
    # split by "\n"
    l = string.split("\n");
    m = []
    for row in l:
      m.append([float(x) for x in row.strip().split(" ")])
     
    return m

  def as_columns(self, matrix):
    # rows is a list of lists.
    # each i is a col
    out = []
    for i in range(0, len(matrix[0])):
      c = []
      for j in range(0, len(matrix)):
        c.append(matrix[j][i])
      out.append(c)

    return out
