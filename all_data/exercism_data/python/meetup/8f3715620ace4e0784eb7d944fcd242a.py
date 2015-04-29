from datetime import date, timedelta
from calendar import monthrange

def calc_day(year, month, start_day, day_of_week, count=1, delta=1):
    delta = timedelta(delta)

    first = True
    _date = date(year, month, start_day)
    while True:
        if not first:
            _date += delta
        else:
            first = False

        if _date.strftime('%A') == day_of_week:
            count -= 1
            if count == 0:
                return _date

        if _date.month != month:
            return 'DID NOT FIND DATE!!! {0}'.format([year, month, start_day, day_of_week, count, delta])            

def meetup_day(year, month, day_of_week, _type):
    if _type == 'teenth':
        return calc_day(year, month, 13, day_of_week)
    elif _type[0].isdigit():
        return calc_day(year, month, 1, day_of_week, count=int(_type[0]))
    elif _type == 'last':
        return calc_day(year, month, monthrange(year,month)[1], day_of_week, delta=-1)
