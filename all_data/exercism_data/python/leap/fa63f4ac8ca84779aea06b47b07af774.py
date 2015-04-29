def is_leap_year(checkYear):

    isLeap = False

    # on every year that is evenly divisible by 4
    if checkYear % 4  == 0:
        isLeap = True
        # except every year that is evenly divisible by 100
        if checkYear % 100 == 0:
            isLeap = False
             # unless the year is also evenly divisible by 400
            if checkYear % 400 == 0:
                isLeap = True

    return isLeap
