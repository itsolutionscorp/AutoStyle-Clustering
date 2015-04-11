from datetime import date, timedelta

# List of days names mapped to weekday numbers.

dayNames =  {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday":4, "Saturday":5, "Sunday":6 }

# A set of functions for returninging a matching element from a list of up to 5 days
filters = {
    "1st":        lambda Lst: Lst[0],    
    "first":      lambda Lst: Lst[0],    
    "2nd":        lambda Lst: Lst[1],
    "3rd":        lambda Lst: Lst[2],
    "4th":        lambda Lst: Lst[3],
    "secondlast": lambda Lst: Lst[-2],
    "last":       lambda Lst: Lst[-1],
    "teenth":     lambda Lst: next(x for x in Lst if x >= 13 and x <= 19)
    }

def meetup_day(year, monthnum, dayname, filterName):

    # Get first of month as date object
    
    dat = date(year, monthnum,1)

    # Get weekday (0=monday etc) for the first of the month
    
    firstDayOfWeek = dat.weekday()

    # Get weekday of desired day (Monday=>0 etc)
    
    dayNum = dayNames[dayname]

    # Get offset to first day with given name in month
    
    firstDayWithName = 1 + dayNum - firstDayOfWeek 

    # If given day was Tuesday and Month starts on Wednesday offset will be egative, 
    # so add 7 to get the following one

    if firstDayWithName < 1:
        firstDayWithName += 7

    # generate a list of day numbers each a week apart eg [3, 10, 17, 24, 31 ]
    # by adding 2, 9, 16, 23, 30 to first day until the month number changes.
    days = []
    for day in range(firstDayWithName, 32, 7):
        if (dat + timedelta(day-1)).month != monthnum:
          break
        days.append(day)

    # Apply the matching filter to the list to get the day 
    # and then generate the corrsponding date object.

    return date(year, monthnum, filters[filterName](days))

