import calendar
from datetime import date


def meetup_day(year, month, day_name, which) -> date:
    '''
    Returns a date object
    '''

    cal = calendar.monthcalendar(year, month)

    day_id = calendar.__getattribute__(day_name.upper())

    # 0 means that day doesn't occur in the month of interest
    possible_days = [i[day_id] for i in cal if i[day_id] != 0]

    if which == "teenth":
        day = list(filter(lambda x: x in range(12, 20), possible_days))[0]
    else:
        which_dict = {'1st': 0,
                      '2nd': 1,
                      '3rd': 2,
                      '4th': 3,
                      'last': -1
                      }

        which_day = which_dict[which]
        day = possible_days[which_day]

    return date(year, month, day)
