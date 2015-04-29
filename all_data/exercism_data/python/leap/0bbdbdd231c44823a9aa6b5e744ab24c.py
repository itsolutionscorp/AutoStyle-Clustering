
class is_leap_year:

    def __new__(self, year):
        year = int(year)
        return not(year % 100 == 0) and year % 4 == 0 or year % 400 == 0
