import calendar
from datetime import date, timedelta


class MeetupDayException(Exception):
    pass

_DAY_NAMES = {day.lower(): i for i, day in enumerate(calendar.day_name)}
_WEEK_NAMES = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, '5th': 4}


def meetup_day(year, month, day_name, week_name):
    try:
        dayno = _DAY_NAMES[day_name.lower()]
    except KeyError:
        raise MeetupDayException('Invalid day name')

    # find start of the meetup week

    if week_name == 'teenth':
        week_start = date(year, month, 13)

    elif week_name == 'last':
        unused, num_days = calendar.monthrange(year, month)
        week_start = date(year, month, 1) + timedelta(days=num_days) - timedelta(weeks=1)

    else:
        try:
            weekno = _WEEK_NAMES[week_name]
        except KeyError:
            raise MeetupDayException('Invalid week name')

        week_start = date(year, month, 1) + timedelta(weeks=weekno)

    # search for the weekday in the found week
    day_offset = dayno - week_start.weekday()
    if day_offset < 0:
        day_offset += 7

    meetup = week_start + timedelta(days=day_offset)
    if meetup.month != month:
        raise MeetupDayException('Day {} not found in the {} week'.format(day_name, week_name))

    return meetup
