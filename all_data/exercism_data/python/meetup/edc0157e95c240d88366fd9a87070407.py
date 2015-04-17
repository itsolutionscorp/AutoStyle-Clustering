__author__ = 'Flavio Miranda'
import calendar
import datetime


def meetup_day(year, month, strday, attr):
    _dates = selectedDate(year, month, strday)
    return {"teenth": [i for i in _dates if 16 >= i.day >= 10][0],
            "1st": _dates[0],
            "2nd": _dates[1],
            "3rd": _dates[2],
            "4th": _dates[3],
            "last": _dates[-1]
    }[attr]


def selectedDate(year, month, strday):
    return [datetime.date(year, month, day) for day in range(1, calendar.monthrange(year, month)[1] + 1) if
            datetime.date(year, month, day).strftime("%A") == strday]