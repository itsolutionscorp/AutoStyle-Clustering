from datetime import date as Date
from datetime import timedelta as Timedelta
import calendar

def meetup_day(year, month, weekday, condition):
    # initialization and error handling
    try: # (enclosed in a try block for code collapsing in editor)
        weekday_abbr    = [day.lower() for day in list(calendar.day_abbr)]
        weekday_dict    = dict(zip(weekday_abbr,range(0,7)))
        condition_dict  = {'1st'    : 0,
                           '2nd'    : 1,
                           '3rd'    : 2,
                           '4th'    : 3,
                           '5th'    : 4, # added some flexibility here
                           'first'  : 0, #
                           'second' : 1, #
                           'third'  : 2, #
                           'fourth' : 3, #
                           'fifth'  : 4, #
                           'last'   :-1,
                           'teenth' : 'teenth'}

        try:
            date    = Date(year, month, 1)  # convert to Date object
        except:
            raise AssertionError("Invalid date type.")

        try:
            weekday = weekday.lower()       # set to lower case and test if string
            weekday = weekday[:3]           # check if at least 3 letters
            weekday = weekday_dict[weekday] # swap value to numeric weekday
        except:
            raise AssertionError("Invalid weekday. Use: " + str(weekday_dict.keys()))

        try:
            condition = condition_dict[condition]   # swap value for numeric condition
        except:
            raise AssertionError("Invalid condition. Use: " + str(condition_dict.keys()))
    except: print("Unknown error.")

    # call getWeekdayDates function to get list of weekdays
    weekdays = getWeekdayDates(date, weekday)

    # return either the teenth or the ith index of weekdays
    if condition == 'teenth':
        for day in weekdays:
            if 13 <= day.day <= 19:
                return day
        print("Calendar error.  Your calendar doesn't have teenths!")
    else:
        return weekdays[condition]

def getWeekdayDates(date, weekday):
    ''' date: Date object - resets day to 1
        weekday: int - 0=mon, 6=sun
        returns: list of dates in month that match weekday
    '''
    date = date.replace(day=1)
    delta = weekday - date.weekday()
    if delta >= 0:
        first_weekday = date + Timedelta(days=delta)
    else:
        first_weekday = date + Timedelta(days=delta+7)

    result = []
    for i in range(5):
        new_date = first_weekday + Timedelta(days=i*7)
        if new_date.month == date.month:
            result.append(new_date)
        else:
            break

    return result
