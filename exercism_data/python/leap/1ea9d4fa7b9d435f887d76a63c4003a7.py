__author__ = 'angelo'


class Year:

    def __init__(self, year):
        self.year = year

    def is_leap_year(self):
        return self.year % 4 == 0 and (self.year % 100 != 0 or self.year % 400 == 0)
