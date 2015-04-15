import datetime
from dateutil.relativedelta import relativedelta

def meetup_day(year, month, day_name, count):
    days = [
        'Monday',
        'Tuesday',
        'Wednesday',
        'Thursday',
        'Friday',
        'Saturday',
        'Sunday'
    ]
    now = datetime.date(year, month, 1)
    end_of_month = now + relativedelta(months=1)
    dates = []

    while now <= end_of_month:
        if days[now.weekday()] == day_name:
            dates.append(now)
        now += datetime.timedelta(days=1)

    if count == "1st":
        return dates[0]
    elif count == "2nd":
        return dates[1]
    elif count == "3rd":
        return dates[2]
    elif count == "4th":
        return dates[3]
    elif count == "5th":
        return dates[4]
    elif count == 'last':
        return dates[-1]
    elif count == 'teenth':
        for date in dates:
            if date.day in range(13, 20):
                return date
