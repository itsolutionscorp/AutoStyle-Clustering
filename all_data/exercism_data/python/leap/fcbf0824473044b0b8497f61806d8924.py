# Created by Paul Olteanu on 24/09/2014
def is_leap_year(year):
    return year % 4 == 0 and (not year % 100 == 0 or year % 400 == 0)
    # Hooray for one-liners!
    # Check for divisibility by 4. If it's divisible by 100 and not by 400, the and statement fails and return false.
    # If it isn't divisible by 100 or it is but it's also divisible by 400, the and statement succeeds and returns true.
