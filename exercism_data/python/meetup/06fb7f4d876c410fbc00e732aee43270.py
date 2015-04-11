from datetime import date, timedelta

WEEKDAYS = ['Monday',
            'Tuesday',
            'Wednesday',
            'Thursday',
            'Friday',
            'Saturday',
            'Sunday']

FIRST_TEENTH = 13

ONE_DAY = timedelta(1)

def meetup_day(y, m, weekday, condition):
    '''
    Take a year, month, weekday (i.e. Monday) and
    a condition (1st, 2nd, 3rd, 4th, or 'teenth')

    returns date object for meetup day
    '''

    # Find a starting date to search from
    if (condition == 'last'):
        # Last will search backwards from end of month
        meetup_date = date(y, m+1, 1)
        meetup_date -= ONE_DAY
    else:
        if (condition == '1st'): day = 1
        elif (condition == '2nd'): day = 8
        elif (condition == '3rd'): day = 15
        elif (condition == '4th'): day = 22
        else: day = FIRST_TEENTH
        meetup_date = date(y, m, day)

    return search_weekday(meetup_date, weekday, condition == 'last')

def search_weekday(d, weekday, reverse=False):
    '''
    Search consecutive dates until desired weekday is found
    '''
    while (WEEKDAYS[d.weekday()] != weekday):
        if (reverse):
            d -= ONE_DAY
        else:
            d += ONE_DAY
    return d
