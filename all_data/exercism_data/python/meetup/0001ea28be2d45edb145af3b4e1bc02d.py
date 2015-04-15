from calendar import Calendar


def meetup(year, month, day, select):
    possible = [date for date in Calendar().itermonthdates(year, month) if date.month == month if date.strftime('%A') == day]
    return decide(select)(possible)


def decide(selection):
    if selection == 'teenth':
        return lambda dates: next(d for d in dates if 13 <= d.day <= 19)

    ix = -1 if (selection == 'last') else (int(selection[0]) - 1)
    return lambda dates: dates[ix]
