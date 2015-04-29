from datetime import date
from datetime import timedelta
import calendar


def meetup_day(year, month, day, ordinal):

    first_day, number_of_days = calendar.monthrange(year, month)
    # print(first_day, number_of_days)

    month_clnd = [(date(year, month, d), date(year, month, d).strftime('%A'))
                  for d in range(1, number_of_days + 1)]
    # print(month_clnd)

    all_day = [d[0] for d in month_clnd if d[1] == day]
    enumerated_day = [(index, dt)for index, dt in enumerate(all_day, start=1)]
    # print(enumerated_day)

    if ordinal == 'last':
        return enumerated_day[-1][1]

    if ordinal == 'teenth':
        for d in enumerated_day:
            if d[1] in [date(year, month, dt)for dt in range(13, 20)]:
                return d[1]
    else:
        ordinal = ordinal[0]
        for d in enumerated_day:
            if str(d[0]) == ordinal:
                return d[1]
