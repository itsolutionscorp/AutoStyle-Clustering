class Year:
    def __init__(self, y):
        self.year = y
        self.is_leap = y % 4 == 0 or y % 100 == 0 and y % 400 != 0

    def is_leap_year(self):
        return self.is_leap

    pass
