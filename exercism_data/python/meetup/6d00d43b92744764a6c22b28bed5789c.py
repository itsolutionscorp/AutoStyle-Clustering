from datetime import date, timedelta
teens = range(13, 20)
whens = {
    'first': 1,
    '1st': 1,
    'second': 2,
    '2nd': 2,
    'third': 3,
    '3rd': 3,
    'fourth': 4,
    '4th': 4,
    'fifth': 5,
    '5th': 5}
days = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6}


def meetup_day(year, month, day, when):

    # check for valid day input
    if day not in days:
        raise Exception('argument exception')

    # start a date at the given year and month
    start = date(year, month, 1)

    # goto the given weekday
    while start.weekday() != days[day]:
        start += timedelta(days=1)

    # add given weeks to date
    if when in whens:
        for i in range(1, whens[when]):
            start += timedelta(days=7)

    # find last week in month
    elif when == 'last':
        check = start + timedelta(days=7)
        while check.month == start.month:
            start += timedelta(days=7)
            check = start + timedelta(days=7)

    # find teenth in month
    elif when == 'teenth':
        while start.day not in teens:
            start += timedelta(days=7)

    # input argument not valid, throw exception
    else:
        raise Exception('argument exception')

    # check if meeting found in month (5th week errors)
    if start.month != month:
        if(year > 1900):
            raise Exception(
                '%s %s not found in %s %s' %
                (when, day, date(year, month, 1).strftime('%B'), year))
        raise Exception(
            '%s %s not found in month %s of year %s' %
            (when, day, month, year))
    return start
