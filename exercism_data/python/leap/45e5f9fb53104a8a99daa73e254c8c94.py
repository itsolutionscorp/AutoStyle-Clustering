from datetime import date

def is_leap_year(year):
    """ 
    datetime.date will throw an ValueError: day is out of range for month
    if you will try set Feb 29 on not-leap year
    """
    try:
        date(year, 2, 29)
    except ValueError, e:
        return False
    else:
        return True

# def is_leap_year(year):

#     return  year % 4 == 0 and \
#             (year % 100 != 0 or \
#             year % 400 == 0)
