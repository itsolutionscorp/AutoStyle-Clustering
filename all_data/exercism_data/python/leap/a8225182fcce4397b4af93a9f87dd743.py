def is_leap_year(the_year):
    if the_year % 4 == 0:

        if (the_year % 400 == 0):
            return True

        if the_year % 100 == 0:
            return False

        return True
    return False
