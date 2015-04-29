import calendar
from datetime import date


_weekday_to_index = {
    'Monday': calendar.MONDAY,
    'Tuesday': calendar.TUESDAY,
    'Wednesday': calendar.WEDNESDAY,
    'Thursday': calendar.THURSDAY,
    'Friday': calendar.FRIDAY,
    'Saturday': calendar.SATURDAY,
    'Sunday': calendar.SUNDAY,
}


_nth_map = {
    '1st': lambda days: days[0][0],
    'first': lambda days: days[0][0],
    '2nd': lambda days: days[1][0],
    'second': lambda days: days[1][0],
    '3rd': lambda days: days[2][0],
    'third': lambda days: days[2][0],
    '4th': lambda days: days[3][0],
    'fourth': lambda days: days[3][0],
    'last': lambda days: days[-1:][0][0],
    'teenth': lambda days: list(
        filter(lambda d: d[0] >= 13 and d[0] <= 19,
                days))[0][0]
}


def nth_to_index(nth: str, days: list) -> int:
    get_index = _nth_map.get(nth)

    if get_index:
        return get_index(days)
    else:
        raise ValueError("%s is not an index in %s" %
                         (nth, sorted(_nth_map.keys())))


def meetup_day(year: int, month: int, day: str, nth: str) -> date:
    cal = calendar.Calendar()

    days = [(md, wd) for md, wd in cal.itermonthdays2(year, month)
            if _weekday_to_index[day] == wd and md != 0]

    target_day = nth_to_index(nth, days)

    return date(year, month, target_day)
