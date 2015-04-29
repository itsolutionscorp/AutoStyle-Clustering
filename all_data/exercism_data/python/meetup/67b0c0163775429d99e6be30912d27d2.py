import calendar
from datetime import date


ordinals = {
    '1st': 0,
    '2nd': 1,
    '3rd': 2,
    '4th': 3,
    '5th': 4,
    'last': -1,
}


def meetup_day(year, month, day_of_week, description):
    day_index = getattr(calendar, day_of_week.upper())
    first_day, last_day = calendar.monthrange(year, month)
    cal = calendar.monthcalendar(year, month)

    if description in ordinals:
        week_index = ordinals[description]
        if day_index < first_day:
            week_index += 1
    else:
        week_index = 1
        if cal[week_index][day_index] < 13:
            week_index += 1

    return date(year, month, cal[week_index][day_index])
