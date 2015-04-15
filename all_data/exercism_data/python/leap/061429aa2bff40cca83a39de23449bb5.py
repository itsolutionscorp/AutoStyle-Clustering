# script for Exercism project leap
# iteration 1

def is_leap_year(year):

    if year % 100 == 0:
        return year % 400 == 0

    return year % 4 == 0
