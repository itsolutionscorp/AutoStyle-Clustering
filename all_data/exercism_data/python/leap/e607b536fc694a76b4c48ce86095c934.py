def is_leap_year(what) :
    return ((what % 4 == 0) and (what % 100 != 0)) or (what % 400 == 0)
