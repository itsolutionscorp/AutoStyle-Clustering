class Matrix:
  def __init__(self, raw):
    self.rows = map(mapRow, raw.split('\n'))

    colCount = len(self.rows[0])
    self.columns = [[] for i in range(0, colCount)]
    for row in self.rows:
      for i in range(0, colCount):
        self.columns[i].append(row[i])

def mapRow(row):
  return map(int, row.strip().split(' '))

def mapNumber(number):
  return int(number.strip())
