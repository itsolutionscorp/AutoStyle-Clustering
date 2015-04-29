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


_modal_index = {
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


def meetup_day(year: int, month: int, day: str, modal: str) -> date:
    cal = calendar.Calendar()

    days = [(md, wd) for md, wd in cal.itermonthdays2(year, month)
            if _weekday_to_index[day] == wd and md != 0]

    try:
        target_day = _modal_index[modal](days)
    except KeyError:
        raise ValueError("%s is not an index in %s" %
                         (modal, sorted(_modal_index.keys())))
    else:
        return date(year, month, target_day)
