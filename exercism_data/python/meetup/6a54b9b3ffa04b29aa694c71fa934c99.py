# figure out the date, given year, month, dayname and occurence
# occurence = [1st, 2nd, 3rd, 4th, last, teenth]

from calendar import monthrange, day_name
from datetime import date


def meetup_day(year, month, day, occurence):

    # kudos to @Silvester23 for this way to generate the weekdays dict
    weekdays = dict(zip(list(day_name), range(7)))
    occurences = {'1st': 0,
                  '2nd': 1,
                  '3rd': 2,
                  '4th': 3,
                  'last': -1}
    candidates = []

    # figure out the potential dates and put them on a list
    for a in range(1, max(monthrange(year, month))+1):
        if date(year, month, a).weekday() == weekdays[day]:
            candidates.append(date(year, month, a))

            # When we have the first potential date, skip 7 ahead
            a += 7

    if occurence == 'teenth':

        # Only two of the candidates are possible for 'teenth'
        for a in range(1, 3):
            if candidates[a].day > 12 and candidates[a].day < 20:
                occurences['teenth'] = a

    return candidates[occurences[occurence]]
