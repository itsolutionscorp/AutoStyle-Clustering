from datetime import date

def weekday_lookup(day):
    dates = {'monday': 0,
             'tuesday': 1,
             'wednesday': 2,
             'thursday': 3,
             'friday': 4,
             'saturday': 5,
             'sunday': 6}
    return dates[day.lower()]

def meetup_day(year, month, day, position):
    '''This function will return a date object for a particular meetup day. See
    README.md for a full description of the problem.'''

    start = 1
    if position == 'teenth':
        start = 13
    elif position == '2nd':
        start = 8
    elif position == '3rd':
        start = 15
    elif position == '4th':
        start = 22
    elif position == 'last':
    # The last week day of a month depends on the number of days in that month. 
        for last in range(28, 32):
            try:
                d = date(year, month, last)
                start = last - 6
            except ValueError:
                break

    # Starting at the date determined by the position argument, we check if the
    # weekday of the date matches the weekday in the day argument.
    for start in range(start, 32):
        start_d = date(year, month, start).weekday()
        if start_d == weekday_lookup(day):
            return date(year, month, start)

        else:
            start_d = date(year, month, start).weekday()
                
