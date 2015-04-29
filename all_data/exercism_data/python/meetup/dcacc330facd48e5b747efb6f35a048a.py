from calendar import Calendar

def meetup_day(year, month, day, position):
    dates = [date for date in Calendar().itermonthdates(year, month)
                  if date.month == month
                  if date.strftime('%A') == day]
    
    if position == 'teenth':
        return next(date for date in dates if 13 <= date.day <= 19)

    return dates[-1 if position == 'last' else int(position[0]) - 1]
