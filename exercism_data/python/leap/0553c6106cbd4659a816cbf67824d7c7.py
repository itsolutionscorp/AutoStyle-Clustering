__author__ = 'jeffmarkey'

def is_leap_year(year):

    leap = False

    # on every year that is evenly divisible by 4
    if(year % 4 == 0):
        leap = True
        #   except every year that is evenly divisible by 100
        if(year % 100 == 0):
            leap = False
            #     unless the year is also evenly divisible by 400
            if(year % 400 == 0):
                leap = True
    return leap
