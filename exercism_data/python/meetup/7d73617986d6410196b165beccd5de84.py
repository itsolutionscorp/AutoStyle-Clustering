from collections import defaultdict
from datetime import date

weekday_map = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
ordinal_map = {'first': 0, 'second': 1, 'third': 2, 'fourth': 3, 'last': -1}


def meetup_day(year, month, weekday, ordinal):
    month_map = defaultdict(list)
    for day in range(1, 32):
        try:
            candidate = date(year, month, day).weekday()
        except:
            break
        else:
            month_map[candidate].append(day)
    if ordinal == 'teenth':
        if month_map[weekday_map[weekday]][1] >= 10 and month_map[weekday_map[weekday]][1] < 20:
            index = 1
        elif month_map[weekday_map[weekday]][2] >= 10 and month_map[weekday_map[weekday]][2] < 20:
            index = 2
        else:
            index = 3
    else:
        try:
            index = int(ordinal[0]) - 1
        except ValueError:
            index = ordinal_map[ordinal]
    return date(year, month, month_map[weekday_map[weekday]][index])


# EOF
