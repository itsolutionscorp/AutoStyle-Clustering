class Series(object):
  def __init__(self, series):
    self.series = map(int, list(series))

  def slices(self, n):
    if n <= 0 or n > len(self.series):
      raise ValueError("Invalid slice length for this series: {0}".format(n))
    else:
      return [self.series [i:i + n] for i in range(len(self.series) - n + 1 )]
