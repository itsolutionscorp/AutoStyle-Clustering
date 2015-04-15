class Year(object):
    def __init__(self, yr):
        self.year = yr

    def is_leap_year(self):
        return ((self.year % 4 == 0) and
                ((self.year % 100 != 0) or
                 (self.year % 400) == 0))
