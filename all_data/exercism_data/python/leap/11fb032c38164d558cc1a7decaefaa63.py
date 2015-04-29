def is_leap_year(year):
    """
    Leap years occur:
        on every year that is evenly divisible by 4
        except every year that is evenly divisible by 100
        unless the year is also evenly divisible by 400
    """
    
    div_by_four = year % 4 == 0
    div_by_hundred = year % 100 == 0
    div_by_fourhundred = year % 400 == 0

    leap_year = div_by_four and (not div_by_hundred or div_by_fourhundred)

    return leap_year
