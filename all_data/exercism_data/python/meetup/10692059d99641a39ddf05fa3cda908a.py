import calendar
import datetime
def meetup_day(year, month, day, number):
    month_days =  calendar.monthrange(year,month)

    # create dictionary with key:value for indexing of days tuple of day in month
    key = {'1st':0, '2nd':1, '3rd':2, '4th':3, 'last':-1, 'first':0}

    # Get the difference first day of month and 'day' using 0-6 weekday scale.
    offset = [x for x in calendar.day_name].index(day) - month_days[0]

    # Correct for negative offset (if day falls before first day of month)
    if offset < 0:
        offset += 7

    # Create tuple of all possible dates for 'day' in month
    days = (offset+1,offset+8,offset+15,offset+22, offset+29)

    # Teenth day cannot use index so we set up special method for this, just check if it's within 13-19
    # for all possible dates in month for that day.
    if number == 'teenth':
        for x in days:
            if x >= 13 and x <= 19:
                new_day = x
                break

    # Otherwise just get the day that matches the index, unless it goes over days in month, then take next from last
    else:
        new_day = days[key[number]]
        if new_day > month_days[1]:
            new_day = days[-2]

    return datetime.date(year, month, new_day)
