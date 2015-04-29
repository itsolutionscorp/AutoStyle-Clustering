def is_leap_year(year):
    # Leap year if evenly divisible by 4, not evenly divisible by 100,
    # or evenly divisible by 400.
    return (year % 4 == 0) and (not (year % 100 == 0) or (year % 400 == 0))
