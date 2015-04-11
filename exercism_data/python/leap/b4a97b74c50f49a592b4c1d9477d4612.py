def is_leap_year(year):
    # return (divisible by 4) and (NOT divisible by 100 UNLESS divisible by 400)
    # (This even covers low value leap years!)
    return year % 4 == 0 and (year % 400 == 0 or year % 100 != 0)
