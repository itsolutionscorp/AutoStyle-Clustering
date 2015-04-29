import datetime as dt

days = 'Monday Tuesday Wednesday Thursday Friday Saturday Sunday'.split()
day_by_number = dict(zip(days, range(len(days))))

number_in_month = {'1st': 0,
                   '2nd': 1,
                   '3rd': 2,
                   '4th': 3,
                   'last': -1}


def create_days_of_month(month, year):
    days_of_month = []

    current_date = dt.date(year, month, 1)
    while current_date.month == month:
        days_of_month.append(current_date)
        current_date += dt.timedelta(days=1)

    return days_of_month


def meetup_day(year, month, day_of_week, number):
    day_of_week_in_month = [day for day in create_days_of_month(month, year) if
                            day.weekday() == day_by_number[day_of_week]]

    if number == 'teenth':
        return [day for day in day_of_week_in_month if day.day >= 13][0]
    else:
        return day_of_week_in_month[number_in_month[number]]
