
class is_leap_year:

    def __new__(self, year):
        year = int(year)
        if not(year % 100 == 0) and year % 4 == 0 or year % 400 == 0:
            return True
        else:
            return False
