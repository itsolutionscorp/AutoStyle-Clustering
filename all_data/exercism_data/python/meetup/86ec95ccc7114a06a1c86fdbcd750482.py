from calendar import Calendar 

def meetup_day(year, month, day_of_week, _type):
    possible = filter(lambda d:d.month == month and d.strftime('%A') == day_of_week, Calendar().itermonthdates(year, month))
    if _type == 'teenth':
        return filter(lambda d:d.day in range(13,20), possible)[0] 

    # either 1-5 or last
    return possible[int(_type[0])-1 if _type[0].isdigit() else -1]
