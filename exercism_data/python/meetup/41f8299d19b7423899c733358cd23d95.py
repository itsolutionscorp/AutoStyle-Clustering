from datetime import date, timedelta
weekdays = [
    'Monday', 
    'Tuesday', 
    'Wednesday', 
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday', 
    ]

def meetup_day(year, month, day, which):
    first_day = date(year, month, 1)
    first_day_weekday = first_day.weekday()
    wanted_day_weekday = weekdays.index(day)
    day_diff = (wanted_day_weekday-first_day_weekday) % 7
    first_desired_day = first_day + timedelta(days=day_diff)
    return _get_adjusted_date(first_desired_day, which)

def _get_adjusted_date(first_day, which):
    return { 
        'teenth': _get_teenth(first_day),
        '1st': first_day,
        '2nd': first_day + timedelta(days=7),
        '3rd': first_day + timedelta(days=14),
        '4th': first_day + timedelta(days=21),
        'last': _get_last(first_day),
        }[which]

def _get_teenth(date):
    while(date.day not in range(13,20)):
        date += timedelta(days=7)
    return date

def _get_last(first_day):
    four_weeks = first_day + timedelta(days=28)
    three_weeks = first_day + timedelta(days=21)
    if ( four_weeks.month == first_day.month):
        return four_weeks
    else:
        return three_weeks
