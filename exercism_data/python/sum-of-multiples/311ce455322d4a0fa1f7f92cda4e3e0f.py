class SumOfMultiples:

  def __init__(self, *args):
    self.divisors = args or [3 ,5]
    return

  def to(self, limit):
    return sum(
      value for value in range(1,limit)
        if any(value % divisor == 0 for divisor in self.divisors))
