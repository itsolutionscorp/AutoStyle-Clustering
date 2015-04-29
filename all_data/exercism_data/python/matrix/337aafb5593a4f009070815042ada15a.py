class Matrix:
  def __init__(self, matrixStr):
    self.rows = [map(int, x.split(" ")) for x in matrixStr.split("\n")]
    length = len(matrixStr.split("\n"))
    self.columns = [self._columnForIndex(i) for i in xrange(length-1)]
    
  def _columnForIndex(self, index):
    return [x[index] for x in self.rows]
