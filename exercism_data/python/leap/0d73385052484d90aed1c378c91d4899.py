# Leap year
# Made by ez.
#

def is_leap_year(num):
    return ((num % 4 == 0) and (num % 100 != 0)) or (num % 400 == 0)
