__author__ = 'jeffmarkey'
import datetime
import calendar

def meetup_day(year, month, weekday, category):

    # Monday is 0 and Sunday is 6
    days = {
    'Monday' : 0,
    'Tuesday' : 1,
    'Wednesday' : 2,
    'Thursday' : 3,
    'Friday' : 4,
    'Saturday' : 5,
    'Sunday' : 6
    }

    # Beginning at a base date.
    start_date = datetime.date(year, month, 1)
    start_day = datetime.date(year, month, 1).weekday()
    delta = (days[weekday] - start_day) % 7

    # Calculating each case...
    if(category == '1st'):
        return start_date + datetime.timedelta(days = delta)
    elif(category == '2nd'):
        return start_date + datetime.timedelta(days = delta+7)
    elif(category == '3rd'):
        return start_date + datetime.timedelta(days = delta+14)
    elif(category == '4th'):
        return start_date + datetime.timedelta(days = delta+21)
    elif(category == 'last'):
        num_days = calendar.monthrange(year, month)[1]
        end_day = datetime.date(year, month, num_days).weekday()
        last_day = num_days - (end_day - days[weekday] % 7)
        return start_date + datetime.timedelta(days = last_day - 1)
    elif(category == 'teenth'):
        start_teenth = datetime.date(year, month, 13).weekday()
        new_delta = (days[weekday] - start_teenth) % 7
        return start_date + datetime.timedelta(days = 12 + new_delta)
