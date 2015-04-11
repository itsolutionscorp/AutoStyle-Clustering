from datetime import date
import calendar

CALENDAR = calendar.Calendar()

# 2nd day of Feb 2015 is a Monday
DAYNUMS = {date(2015, 2, i+2).strftime('%A'): i
           for i in range(0, 7)}

INSTANCE_DICT = {1: '1st', 2: '2nd', 3: '3rd', 4: '4th', 5: '5th'}

def meetup_day(year, month, weekday, instance):
    day_number = DAYNUMS[weekday]
    day_dict = {}
    numero = 0
    for day, weekday_number in CALENDAR.itermonthdays2(year, month):
        if weekday_number == day_number and day > 0:
            numero += 1
            day_dict[INSTANCE_DICT[numero]] = day
            if 13 <= day <= 19:
                day_dict['teenth'] = day
    day_dict['last'] = day_dict[max(day_dict, key=lambda i: day_dict[i])]
    return date(year, month, day_dict[instance])
