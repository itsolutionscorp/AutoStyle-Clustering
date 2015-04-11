import datetime

weekdays = {
    'Monday': 0,
    'Tuesday': 1,
    'Wednesday': 2,
    'Thursday': 3,
    'Friday': 4,
    'Saturday': 5,
    'Sunday': 6
}

def meetup_day(year, month, day_name, week_index_name):
    weekday_index = weekdays[day_name]
    cur_day = datetime.date(year, month, 1)

    # Get first occurrence of desired weekday in month
    while cur_day.weekday() != weekday_index:
        cur_day = cur_day.replace(day=cur_day.day + 1)

    if week_index_name == 'teenth':
        # Increment until in the teens
        while cur_day.day < 13:
            cur_day = cur_day.replace(day=cur_day.day + 7)
    elif week_index_name == 'last':
        # Increment until we try to make an invalid day of the month
        try:
            while True:
                cur_day = cur_day.replace(day=cur_day.day + 7)
        except:
            pass
    else:
        # Add 7 days for each week beyond the first
        cur_day = cur_day.replace(day=cur_day.day + (int(week_index_name[0]) - 1) * 7)

    return cur_day
