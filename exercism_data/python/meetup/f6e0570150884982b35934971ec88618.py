from datetime import MINYEAR, MAXYEAR, date, timedelta
from calendar import monthrange, day_name, month_name
import re

weekRegex = re.compile('(?P<weeks>\d)(?:st|nd|rd|th)', re.UNICODE | re.IGNORECASE)

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

    weekMatch = re.search(weekRegex, mode)

    if weekMatch:
        weeksdelta = int(weekMatch.group('weeks')) - 1
        meetup += timedelta(weeks = weeksdelta)

    if meetup.month != month:
        raise MeetupDayException('There is no matching date in %s %s' % (month_name[month], year))

    return meetup

class MeetupDayException(ValueError):
    pass
