from datetime import date
from calendar import monthcalendar

def meetup_day(year, month, day_of_week, modifier):
    # create a matrix of all the dates in the month
    c = monthcalendar(year, month)

    # this dictionary will be used to translate the day of the week
    day_dict = {
        'Monday': 0,
        'Tuesday': 1,
        'Wednesday': 2,
        'Thursday': 3,
        'Friday': 4,
        'Saturday': 5,
        'Sunday': 6
    }

    # create a list of all the dates in the month on the given day of the week
    day_list = [c[x][day_dict[day_of_week]] for x in range(0, len(c))]
    # remove place-holder zeroes leftover from the original matrix
    for d in day_list:
        if d == 0:
            day_list.remove(d)

    # handle the special case first
    if modifier == 'teenth':
        # here we create a list of all the 'teens' and return our first match
        teenth_list = range(13,20)
        for d in day_list:
            if d in teenth_list:
                return date(year, month, d)
    elif modifier == 'last':
        return date(year, month, day_list[-1])
    else:
        # strip the alphas from the modifier to easily index our day list (e.g. 1st becomes 1, 2nd becomes 2)
        return date(year, month, day_list[int(modifier.translate(None, 'abcdefghijklmnopqrstuvwxyz')) - 1])
