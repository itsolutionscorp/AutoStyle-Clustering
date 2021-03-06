import datetime
import calendar

DAY = {
        'mo': calendar.MONDAY,
        'tu': calendar.TUESDAY,
        'we': calendar.WEDNESDAY,
        'th': calendar.THURSDAY,
        'fr': calendar.FRIDAY,
        'sa': calendar.SATURDAY,
        'su': calendar.SUNDAY,
        }

def meetup_day(year, month, weekday, pos):
    first, maxdays = calendar.monthrange(year, month)
    wd = DAY[weekday[:2].lower()]

    # Get all positions of weekday in month
    wds = [day+1 for day in range(maxdays) if not (day + first - wd) % 7]

    if pos == 'teenth':
        get_day = lambda x: next(e for e in x if 12 < e < 20)
    else:
        if pos[0].isdigit():
            p = int(pos[0]) - 1
        elif pos == 'first':
            p = 0
        elif pos == 'last':
            p = -1
        get_day = lambda x: x[p]

    return datetime.date(year, month, get_day(wds))
