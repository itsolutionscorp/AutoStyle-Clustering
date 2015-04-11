from datetime import date
import calendar

class NoSuchMeetupDayException(Exception):
    pass

_filters = [
    ( [ '1st', 'first'  ], lambda c: c[0] ),
    ( [ '2nd', 'second' ], lambda c: c[1] ),
    ( [ '3rd', 'third'  ], lambda c: c[2] ),
    ( [ '4th', 'fourth' ], lambda c: c[3] ),
    ( [ '5th', 'fifth'  ], lambda c: c[4] ),
    ( [ 'last'          ], lambda c: c[-1] ),
    ( [ 'teenth'        ], lambda c: [ d for d in c if d.day >= 13 ][0] )
]

def meetup_day(year, month, day_name, spec):
    candidates = _get_candidates(year, month, day_name)
    filter = _get_candidate_filter(spec)
    return filter(candidates)

def _get_candidates(year, month, day_name):
    """
    Retrieves a list of all dates within the requested year and month
    for which the day equals the requested day. So it would for example
    return a list of dates for all mondays in September 2014.
    Note: candi"dates" pun intended ;-)
    """
    day_number = _get_day_number(day_name)
    weeks_in_month = calendar.monthcalendar(year, month)
    return [
        date(year, month, week[day_number])
        for week in weeks_in_month  
        if week[day_number] != 0 ]

def _get_day_number(day_name):
    try:
        return calendar.day_name[:].index(day_name)
    except ValueError:
        raise ValueError(day_name + ": unknown day name")

def _get_candidate_filter(spec):
    try:
        filter = [ f for specs, f in _filters if spec in specs ][0]
    except IndexError:
        raise ValueError(spec + ": unknown specifier")

    def filter_with_exception_handler(candidates):
        try:
            return filter(candidates)
        except IndexError:
            raise NoSuchMeetupDayException('No such meet day exists')

    return filter_with_exception_handler
