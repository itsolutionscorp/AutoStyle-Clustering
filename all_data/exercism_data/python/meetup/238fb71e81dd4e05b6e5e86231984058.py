import calendar
from datetime import date, timedelta

DAYS_IN_A_WEEK = 7

TEENTHS = [13,14,15,16,17,18,19]

DAYS_WEEKDAYS_DICT = {
    "Monday": 0,
    "Tuesday": 1,
    "Wednesday": 2,
    "Thursday": 3,
    "Friday": 4,
    "Saturday": 5,
    "Sunday": 6,
}

def meetup_day(year, month, weekday, descriptor):

    possible_meetup_dates = _get_possible_meetup_dates(year, month, weekday)

    if descriptor is '1st': return possible_meetup_dates[0]

    if descriptor is '2nd': return possible_meetup_dates[1]

    if descriptor is '3rd': return possible_meetup_dates[2]

    if descriptor is '4th': return possible_meetup_dates[3]

    if descriptor is '5th': return possible_meetup_dates[4]

    if descriptor is 'teenth':
        return filter(_is_teenth, possible_meetup_dates)[0]

    if descriptor is 'last': return possible_meetup_dates[-1]

def _is_same_weekday_factory(meetup_weekday):

    def is_same_weekday(date):
        return date.weekday() is meetup_weekday

    return is_same_weekday

def _is_teenth(date): return date.day in TEENTHS

def _get_possible_meetup_dates(year, month, weekday):

    cal = calendar.Calendar()
    meetup_weekday = DAYS_WEEKDAYS_DICT[weekday]

    dates = [date for date in cal.itermonthdates(year, month) if date.month == month]
    is_same_weekday = _is_same_weekday_factory(meetup_weekday)

    return filter(is_same_weekday, dates)
