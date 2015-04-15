#
# Defines a function to determine if any given year is a leap year
#
def is_leap_year(year):
    # every four years, excluding centuries that are not divisible by 400
    if (year % 4 == 0) and (not year % 100 == 0 or year % 400 == 0):
        return True
    return False
       
