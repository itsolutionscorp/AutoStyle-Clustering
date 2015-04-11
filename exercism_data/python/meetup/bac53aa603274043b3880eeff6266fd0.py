'''meetup exercise'''

import datetime


def meetup_day(year, month, day_of_week, specifier):
    '''return the date of the meeting for the specified year/month based
    on day of week and the specifier
    year (int):
    month (int): 1-12
    day_of_week (str): Monday, ..., Sunday
    specifier (str): 1st, 2nd, 3rd, 4th, last, teenth
    '''
    dow = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday',
           'Saturday', 'Sunday']
    target_dow = dow.index(day_of_week)

    if specifier == 'teenth':
        teenth = [13, 14, 15, 16, 17, 18, 19]
        for cur_dow in teenth:
            cur_day = datetime.date(year, month, cur_dow)
            if cur_day.weekday() == target_dow:
                found_day = cur_day

    elif specifier == 'last':
        the_day = 21    # shortest month is 28 days, start here so we'll find
        while True:
            try:
                cur_day = datetime.date(year, month, the_day)
                if cur_day.weekday() == target_dow:
                    found_day = cur_day
                the_day += 1
            except ValueError:
                # the last valid date matching target_dow, is last
                break

    else:
        weeks = ['1st', '2nd', '3rd', '4th']
        the_day = 0
        found_day = False
        while not found_day:
            the_day += 1
            cur_day = datetime.date(year, month, the_day)
            if cur_day.weekday() == target_dow:
                found_day = cur_day     # we found date, so set and exit loop
        # now advance the number of weeks desired
        the_day += 7 * weeks.index(specifier)
        found_day = datetime.date(year, month, the_day)

    return found_day
