class Year:
    """ Year determins if a year is a leap year """

    def __init__(self, year):
        self.year = year

    def is_leap_year(self):
        return self.year % 4 == 0 and (self.year % 400 == 0 or self.year % 100 != 0)
