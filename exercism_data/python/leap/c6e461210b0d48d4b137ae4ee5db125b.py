''' Script to determine leap year'''
#

def is_leap_year(year):
    leap_year = False 
    # To be a leap year, the year must be evenly divisible by 4
    # It is not a leap year if evenly divisible by 100,
    # unless the year is also divisible by 400
    if year % 4 == 0:
        leap_year = True
    if year % 100 == 0:
        leap_year = False 
    if year % 100 == 0 and year % 400 == 0:
        leap_year = True
    return leap_year
