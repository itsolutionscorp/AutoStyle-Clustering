#
# exercism.com: python/leap
# Determine if given year is a leap year

def is_leap_year(year):
    """Determine if year is a leap year

    on every year that is evenly divisible by 4
    except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400"""

    return year % 400 == 0 or (year % 4 == 0 and not year % 100 == 0)
