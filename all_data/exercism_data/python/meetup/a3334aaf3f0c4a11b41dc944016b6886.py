import calendar, datetime

cal = calendar.Calendar()


def meetup_day(year, month, day_of_week, position):
    week_map = dict(zip(calendar.day_name, cal.iterweekdays()))
    month_days = cal.monthdays2calendar(year, month)

    # Flatten the list of lists, filter by day of week.
    valid = [pair for week in month_days for pair in week if pair[1] == week_map.get(day_of_week) and pair[0] != 0]

    index = position[:1]

    if index.isnumeric():
        day = valid[int(index) - 1][0]
    elif position == 'last':
        day = valid[-1][0]
    elif position == 'teenth':
        day = [dayteen[0] for dayteen in valid if dayteen[0] in range(13, 19)][0]

    return datetime.date(year, month, day)

if __name__ == '__main__':
    meetup_day(2013, 2, 'Wednesday', 'teenth')
