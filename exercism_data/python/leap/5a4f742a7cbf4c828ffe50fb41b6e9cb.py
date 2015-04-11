#Returns true if year is evenly divisible by 4,
#and either isn't divisible by 100or is divisible by 400.
def is_leap_year(year):
    return year % 4 == 0 and (not year % 100 == 0 or year % 400 == 0)
