class Matrix:
  def __init__(self, numbers):
    self.rows = []
    self.columns = []

    for row in numbers.split('\n'):
      self.rows.append([int(x) for x in row.split(' ')])

    for col in self.rows[0]:
      self.columns.append([])

    for row in self.rows:
      for i, digit in enumerate(row):
        self.columns[i].append(digit)
