def is_leap_year(year):
    """ Determines whether or not the submitted "year" is a leap year
        It is a leap year if:
            - The year is evenly divisible by 4
            - The year is not evenly divisible by 100
            - The year can be evenly divisible by 400
    """
    def divisible_by_four(year):
        if year % 4 == 0:
            return True

    def divisible_by_100(year):
        if year % 100 == 0:
            return True

    def divisible_by_400(year):
        if year % 400 == 0:
            return True

    if divisible_by_400(year):
        return True

    if divisible_by_100(year):
        return False

    if divisible_by_four(year):
        return True
    else:
        return False

    #
    # return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)
