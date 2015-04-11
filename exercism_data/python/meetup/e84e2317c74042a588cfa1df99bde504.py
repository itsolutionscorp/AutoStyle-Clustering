import calendar
import datetime

# cal.monthdays2calendar(year, mo)
# mar = cal.monthdays2calendar(2013, 3)
# [[(0, 6), (0, 0), (0, 1), (0, 2), (0, 3), (1, 4), (2, 5)],
# [(3, 6), (4, 0), (5, 1), (6, 2), (7, 3), (8, 4), (9, 5)],
# [(10, 6), (11, 0), (12, 1), (13, 2), (14, 3), (15, 4), (16, 5)],
# [(17, 6), (18, 0), (19, 1), (20, 2), (21, 3), (22, 4), (23, 5)],
# [(24, 6), (25, 0), (26, 1), (27, 2), (28, 3), (29, 4), (30, 5)],
# [(31, 6), (0, 0), (0, 1), (0, 2), (0, 3), (0, 4), (0, 5)]]


def meetup_day(year, month, day_name, position):
    day_map = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    cal = calendar.Calendar(6)
    month_cal = cal.monthdays2calendar(year, 3)
    position_map = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1}
    if position in position_map:
        pos = position_map[position]
        week = month_cal[pos]
        day = week[day_map[day_name]]

    elif position == 'teenth':
        days = []
        for week in month:
            for d in week:
                days.extend(d)
        candidates = [d for d in days if 20 > d[0] > 12]
        day_tuple = [x for x in candidates if x[1] == day_map[day_name]]
        day = day_tuple[0][0]

    else:
        return datetime.date(1, 1, 1)

    return datetime.date(year, month, day)
