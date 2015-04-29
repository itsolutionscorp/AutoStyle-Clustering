def is_leap_year(year):
    if divmod(year, 4)[1] == 0:
        if divmod(year, 100)[1] == 0:
            if divmod(year, 400)[1] == 0:
                return True
            else:
                return False
        else:
            return True
    else:
        return False
