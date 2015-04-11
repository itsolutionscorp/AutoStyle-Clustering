class SumOfMultiples():
  def __init__(self, *args):
    self.multiples = []
    if args:
      for i in args:
        self.multiples.append(i)
    else:
      self.multiples = [3, 5]

  def to(self, n):
    sum = 0
    print self.multiples
    for i in xrange(2, n):
      for j in self.multiples:
        if i % j == 0:
          print sum
          sum += i
    return sum
