def is_leap_year(input):
    return input % 400 == 0 or (input % 100 != 0 and input % 4 == 0)
