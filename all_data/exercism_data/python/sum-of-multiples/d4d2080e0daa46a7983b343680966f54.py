class SumOfMultiples:

  def __init__(self, *args):
    self.divisors = args or [3 ,5]
    return

  def to(self, limit):
    return sum([
      x for x in range(1,limit)
        if [ n for n in self.divisors if x % n == 0]])
