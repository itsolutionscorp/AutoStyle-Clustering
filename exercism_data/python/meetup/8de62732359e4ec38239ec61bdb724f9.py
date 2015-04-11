import datetime


MODIFIERS = {'1st': 0, 'first': 0,
             '2nd': 1,
             '3rd': 2,
             '4th': 3,
             'last': -1}


def get_weekday(date):
    """ Given a datetime.datetime
    returns the name of the weekday """
    return date.strftime('%A')


def first_weekday(date, weekday):
    """ Increments the given date
    until the date's weekday matches
    the given weekday """
    while get_weekday(date) != weekday:
        date = date + datetime.timedelta(days=1)
    return date


def add_week(date):
    """ Adds 7 days to a date """
    return date + datetime.timedelta(days=7)


def all_weekday_in_month(year, month, weekday):
    """ Returns a list of every weekday (ie Monday) in a month """
    date = datetime.datetime(year, month, 1)
    date = first_weekday(date, weekday)
    ret_dates = [date]
    month = date.month
    while True:
        date = add_week(date)
        if date.month != month:
            break
        ret_dates.append(date)
    return ret_dates


def meetup_day(int_year, int_month, str_weekday, str_modifier):
    all_dates = all_weekday_in_month(int_year, int_month, str_weekday)

    if str_modifier in MODIFIERS:
        return all_dates[MODIFIERS[str_modifier]].date()

    if str_modifier == 'teenth':
        for date in all_dates:
            if 19 >= date.day >= 13:
                return date.date()
