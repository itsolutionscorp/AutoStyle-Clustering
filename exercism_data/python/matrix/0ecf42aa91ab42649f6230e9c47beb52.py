class Matrix(object):
  """docstring for Matrix"""
  def __init__(self, raw):
    super(Matrix, self).__init__()
    self.raw = raw

  @property  
  def rows(self):
    return map(
      lambda row:
        map(
          lambda cell:
            int(cell),
          row.split(" ")
        ),
      map(lambda x: x.strip(), self.raw.split("\n"))
    )

  @property
  def columns(self):
    rows = self.rows
    cols = []
    for col in range(len(rows[0])):
      cols.append([])
      for row in range(len(rows)):
        cols[col].append(rows[row][col])
    return cols
