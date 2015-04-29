from calendar import Calendar
from datetime import date

def meetup_day(year, month, weekday, which):
    '''
    determines the date that corresponds to a given
    weekday in a month
    '''

    dates = [date
                for date in Calendar().itermonthdates(year, month)
                if date.month == month
                if date.strftime('%A') == weekday]

    return _select_date(dates, which)


def _select_date(dates, which):

    return (_select_date_teenth(dates) if which == 'teenth'
                else _select_date_position(dates, which))


def _select_date_teenth(dates):

    return filter(lambda x: 13 <= x.day <= 19, dates)[0]


def _select_date_position(dates, position):

    index = (-1 if position == 'last'
                    else int(position[0]) - 1)

    return dates[index]
