def is_leap_year(the_year):
    if the_year % 100 == 0:
        # if the_year is a centurial year then it has to be divisible by 400
        return (the_year % 400) == 0
    else:
        # otherwise the_year has to be divisible by 4
        return (the_year % 4) == 0
