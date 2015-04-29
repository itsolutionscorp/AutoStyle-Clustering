from datetime import date, timedelta
import calendar

one_day   = timedelta(days = 1)
one_week  = timedelta(days = 7)

class NoSuchMeetupDayException(Exception):
    pass

def meetup_day(year, month, day_name, spec):
    date = _get_first_date_matching_day(year, month, day_name)
    try:
      return _dispatch[spec](date)
    except KeyError:
      raise ValueError(spec + ": unknown specifier"); 

def _get_first_date_matching_day(year, month, day_name):
    week_day = getattr(calendar, day_name.upper())
    matching_date = date(year, month, 1)
    while week_day != matching_date.weekday():
        matching_date += one_day
    return matching_date;

def _handle_first(date):
    return date

def _handle_second(date):
    return date + one_week

def _handle_third(date):
    return date + 2 * one_week

def _handle_fourth(date):
    return date + 3 * one_week

def _handle_fifth(date):
    fifth = date + 4 * one_week
    if fifth.month != date.month:
        raise NoSuchMeetupDayException("No fifth exists in month") 
    return fifth

def _handle_last(date):
    try:
        return _handle_fifth(date)
    except NoSuchMeetupDayException:
        return _handle_fourth(date)

def _handle_teenth(date):
    while date.day < 13:
        date += one_week
    return date 

_dispatch = {
    '1st'    : _handle_first,
    'first'  : _handle_first,
    '2nd'    : _handle_second,
    'second' : _handle_second,
    '3rd'    : _handle_third,
    'third'  : _handle_third,
    '4th'    : _handle_fourth,
    'fourth' : _handle_fourth,
    '5th'    : _handle_fifth,
    'fifth'  : _handle_fifth,
    'last'   : _handle_last,
    'teenth' : _handle_teenth
}
