
from datetime import date
import calendar

_WEEKDAYS = ["Monday", "Tuesday" ,"Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
_ORDINALS = ["1st", "2nd", "3rd", "4th"]

def meetup_day(year, month, weekday, ordinal):
    month_start_weekday = date(year, month, 1).weekday()
    
    day = (_WEEKDAYS.index(weekday) - month_start_weekday) % 7 + 1

    week_delta = 0
    
    if ordinal in _ORDINALS:
        week_delta = _ORDINALS.index(ordinal)
    elif ordinal == "teenth":
        week_delta = (19 - day)//7
    elif ordinal == "last":
        last_day = calendar.monthrange(year, month)[1]
        week_delta = (last_day - day)//7

    day += 7 * week_delta

    return date(year, month, day)
