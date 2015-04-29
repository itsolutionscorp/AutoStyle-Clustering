import operator

def multiplesOfXUptoN(x, n):
  for i in range(int(n/x)):
    yield i*x

class SumOfMultiples:
  def __init__(self, *args):
    if not args:
      args = (3, 5)
    self.args = args
    return

  def to(self, n):
    s=0
    for i in self.args:
      s += sum(x for x in xrange(i, n, i))
    from itertools import combinations
    for i in range(2, len(self.args)+1):
      for cmbn in combinations(self.args, i):
        multiple = reduce(operator.mul, cmbn)
        sumOfMultiples = sum(multiplesOfXUptoN(multiple, n))
        s -= sumOfMultiples
    return s
