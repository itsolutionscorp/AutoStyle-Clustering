class SumOfMultiples:
  def __init__(self, *args):
    self.args = args or (3, 5)

  def to(self, n):
    return sum([x for x in xrange(n) if any(x % i == 0 for i in self.args)])
