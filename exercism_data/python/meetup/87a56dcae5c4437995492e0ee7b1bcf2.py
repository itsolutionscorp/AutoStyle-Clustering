import calendar


DOW = {'Monday': 0,
       'Tuesday': 1,
       'Wednesday': 2,
       'Thursday': 3,
       'Friday': 4,
       'Saturday': 5,
       'Sunday': 6}

REPEATERS = {'1st': 0,
             '2nd': 1,
             '3rd': 2,
             '4th': 3,
             'last': -1}


def find_teenth(days_of_week):
    for day in days_of_week:
        if day.day > 12 and day.day < 20:
            return day


def meetup_day(year, month, day_of_week, repeater):
    days_in_month = calendar.Calendar().itermonthdates(year, month)

    days_of_week = [day for day in days_in_month
                    if day.weekday() == DOW[day_of_week] and day.month == month]

    if repeater == 'teenth':
        return find_teenth(days_of_week)

    return days_of_week[REPEATERS[repeater]]
