class Year(object):
    def __init__(self, val):
        self.val = val
    def is_divisible_by(self, divisor):
        return self.val % divisor == 0

def is_leap_year(year_val):
    year = Year(year_val)
    if year.is_divisible_by(400):
        return True
    if year.is_divisible_by(100):
        return False
    if year.is_divisible_by(4):
        return True
    return False
