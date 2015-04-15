import calendar
import datetime

def meetup_day(year, month, day_text, meeting):

    # Index: Monday is 0 ... Sunday is 6
    day_number = list(calendar.day_name).index(day_text)

    # determine the starting day for the search
    if meeting == 'last':
        start_day = max(calendar.monthrange(year, month)) # last day of the month

    elif meeting == 'teenth': # 13 to 19
        start_day = 13

    else: # default
        start_day = 1

    date_object = datetime.date(year, month, start_day)


    if meeting == 'last': # handle the last day of the month going backwards

        while date_object.weekday() != day_number:
            date_object -= datetime.timedelta(days=1) # go backwards


    else: # handle everything else going forward

        while date_object.weekday() != day_number:
            date_object += datetime.timedelta(days=1) # try the next day

        for _ in xrange({'2nd' : 1, '3rd' : 2, '4th' : 3}.get(meeting, 0)):

            date_object += datetime.timedelta(days=1) # go to the next day

            while date_object.weekday() != day_number:
                date_object += datetime.timedelta(days=1) # try the next day


    return date_object
