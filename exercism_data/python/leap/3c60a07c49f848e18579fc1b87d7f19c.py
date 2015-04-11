class Year:
  
  def is_leap_year(self, y):

    self.y = y

    if self.yearIsDivisibleBy(4):

      if self.yearIsDivisibleBy(400):
        return True

      if not self.yearIsDivisibleBy(100):
        return True

    return False




  def yearIsDivisibleBy(self, b):
    return self.y % b == 0
