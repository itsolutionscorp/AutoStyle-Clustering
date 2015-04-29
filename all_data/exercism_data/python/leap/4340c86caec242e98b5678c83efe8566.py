class Year(int):
    def is_leap_year(self):
        return self % 4 == 0 and self % 400 not in [100, 200, 300]
