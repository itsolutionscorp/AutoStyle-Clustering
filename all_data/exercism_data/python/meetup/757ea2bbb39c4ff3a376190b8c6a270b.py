from datetime import date, timedelta

def meetup_day(year, month, day_name, date_type):
    weekdays = ['Monday', 'Tuesday', 'Wednesday', 'Thursday',
                'Friday', 'Saturday', 'Sunday']
    operators = {
        '1st': lambda days: days[0],
        '2nd': lambda days: days[1],
        '3rd': lambda days: days[2],
        '4th': lambda days: days[3],
        'last': lambda days: days[-1],
        'teenth': lambda days: [d for d in days if 13 <= d <= 19][0],
    }

    days = []
    day = date(year=year, month=month, day=1)
    while day.month == month:
        if weekdays[day.weekday()] == day_name:
            days.append(day.day)

        day += timedelta(1)

    return date(year=year, month=month, day=operators[date_type](days))
