from calendar import Calendar, day_name, weekday


_calendar = Calendar()

_daynumbers = { k: v for k, v in zip(day_name, range(7)) }


def _getdays(year, month, dayname):
    day = _daynumbers[dayname]
    return list(filter(lambda d: month == d.month and
                                 day == weekday(d.year, d.month, d.day),
                       _calendar.itermonthdates(year, month)))


def meetup_day(year, month, dayname, pos):
    days = _getdays(year, month, dayname)
    if pos == "last":
        return days[-1]
    elif pos == "teenth":
        return next(filter(lambda d: d.day >= 10, days))
    else:
        return days[int(pos[0]) - 1]
