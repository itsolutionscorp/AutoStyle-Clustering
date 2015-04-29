#compact as possible!
def is_leap_year(year):
    if year >= 1752:
        return year % 400 == 0 or year % 100 != 0 and year % 4 == 0

    return True if year % 4 == 0 else False
