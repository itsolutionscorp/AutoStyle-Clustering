class Year(object):
    def __init__(self, val):
        self.val = val
    def is_divisible_by(self, divisor):
        return self.val % divisor == 0

def is_leap_year(year_val):
    year = Year(year_val)
    if not year.is_divisible_by(4):
        return False
    if year.is_divisible_by(100) and not year.is_divisible_by(400):
        return False
    return True
