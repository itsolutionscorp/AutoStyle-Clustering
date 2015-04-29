from datetime import MINYEAR, MAXYEAR, date, timedelta
from calendar import monthrange, day_name, month_name

def meetup_day(year, month, day, mode):
    if not MINYEAR <= year <= MAXYEAR or not 1 <= month <= 12:
        raise MeetupDayException('Invalid year or month')

    startDay = 1

    if mode == 'last':
        startDay = monthrange(year, month)[1]
    elif mode == 'teenth':
        startDay = 13

    meetup = date(year, month, startDay)
    targetDay = list(day_name).index(day)
    daydelta = -1 if mode == 'last' else 1

    while not meetup.weekday() == targetDay:
        meetup += timedelta(days = daydelta)

    if mode[0].isdigit():
        weeksdelta = int(mode[0]) - 1
        meetup += timedelta(weeks = weeksdelta)

        if meetup.month != month:
            raise MeetupDayException('There is no %s %s in %s of %s' % (mode, day, month_name[month], year))

    return meetup

class MeetupDayException(ValueError):
    pass
