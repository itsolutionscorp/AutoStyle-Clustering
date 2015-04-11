def is_leap_year(number):
    return number % 4 == 0 and (number % 100 != 0 or number % 400 == 0)
