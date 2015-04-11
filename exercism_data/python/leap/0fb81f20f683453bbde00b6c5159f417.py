def is_leap_year(year):
    return evenly_divisible(year, 4) and (
        not evenly_divisible(year, 100) or evenly_divisible(year, 400)
    )
    
def evenly_divisible(x, y):
    ## checks if x is evenly divisble by y
    return not x % y
