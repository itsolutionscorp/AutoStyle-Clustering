def is_leap_year(year):
    divisible_by_4 = year % 4 == 0
    divisible_by_100 = year % 100 == 0
    divisible_by_400 = year % 400 == 0

    # Leap year in the case that a year is divisible by 4 and
    # divisible by both 100 and 400 or neither.
    return divisible_by_4 and (divisible_by_100 == divisible_by_400)
