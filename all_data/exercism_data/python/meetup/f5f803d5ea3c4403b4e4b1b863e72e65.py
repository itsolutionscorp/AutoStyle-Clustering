import datetime as dt

def meetup_day(year, month, weekday, spec,
               _wkday=dict((w, i) for i, w in
                           enumerate('Mo Tu We Th Fr Sa Su'.split()))):

    wanted = _wkday[weekday[:2]]
    is_wanted_wkday = lambda d: d.weekday() == wanted

    if spec == 'teenth':
        which = 0
        keepit = lambda d: is_wanted_wkday(d) and 12 < d.day < 20
    else:
        which = -1 if spec == 'last' else int(spec[0]) - 1
        keepit = is_wanted_wkday

    def get_month_data(y, m, _oneday=dt.timedelta(1, 0)):
        d = dt.date(y, m, 1)
        while d.month == m:
            yield d
            d += _oneday

    return [d for d in get_month_data(year, month) if keepit(d)][which]
