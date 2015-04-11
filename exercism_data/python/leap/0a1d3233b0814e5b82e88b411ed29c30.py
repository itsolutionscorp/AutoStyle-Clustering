def is_leap_year(year):
    if type(year) != int or year < 0:
        raise TypeError("Please Input a Valid Year")
    if year % 4 == 0:
        if year % 100 == 0 and not year % 400 == 0:
            return False
        return True
    return False
