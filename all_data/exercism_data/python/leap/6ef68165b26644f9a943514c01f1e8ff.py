def is_leap_year( year ):
    """returns if given year is a leap year
    leap years occur in years evenly divible by 4
    except for years evenly divisible by 100
    unless the year is evenly divisible by 400
    """

    if year % 400 == 0:
        return True
    elif year % 100 == 0:
        return False
    elif year % 4 == 0:
        return True

    return False
