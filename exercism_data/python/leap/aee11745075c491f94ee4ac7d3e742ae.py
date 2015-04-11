# Checks if a given year is a leap year

def is_leap_year(year):

    # Checks if user gave an int as an argument
    if not type(year) is int:
        return "Not a valid year, please try again. (A year should be a number.)"

    # a leap year is either divisible by 4 and not by 100 or it is divisible by 400
    if (year % 4 == 0 and year % 100 != 0) or year % 400 == 0:
        return True
    else:
        return False
