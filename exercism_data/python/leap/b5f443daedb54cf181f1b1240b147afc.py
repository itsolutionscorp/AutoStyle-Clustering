#  leap_year:  find out if the submitted year is a leap_year
def is_leap_year(year):

    response = False
    if (year % 4 ==0) and (year % 100 != 0 or year % 400 ==0):            
       response = True 

    return response
