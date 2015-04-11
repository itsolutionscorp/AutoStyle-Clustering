def is_leap_year(year):
    #special 100 and 400 year case
    #"except every year that is evenly divisible by 100"
    #"unless the year is also evenly divisible by 400"
    if year % 100 == 0:
        if year % 400 == 0:
            return True
        return False

    #general "on every year that is evenly divisible by 4" case
    return year % 4 == 0
