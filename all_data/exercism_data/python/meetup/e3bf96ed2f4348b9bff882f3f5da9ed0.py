import datetime, calendar

def meetup_day(year, month, day_name, nth_day):
    nth_conditions = {
        '1st': lambda day_list: day_list[0],
        '2nd': lambda day_list: day_list[1],
        '3rd': lambda day_list: day_list[2],
        '4th': lambda day_list: day_list[3],
        'last': lambda day_list: day_list[-1],
        'teenth': lambda day_list: [day for day in day_list if 13 <= day.day <= 19][0]
    }

    days_in_month = calendar.monthrange(year, month)[1]
    list_of_days = []
    for i in xrange(days_in_month):
        day = datetime.date(year, month, i+1)
        if day.strftime("%A") == day_name:
            list_of_days.append(day)

    return nth_conditions[nth_day](list_of_days)
