from datetime import date
import calendar

int_to_day_in_week = (
    'Monday',
    'Tuesday',
    'Wednesday',
    'Thursday',
    'Friday',
    'Saturday',
    'Sunday'
)

def meetup_day(year, month, day_in_week, iteration):
    days_in_week = {
        'Monday':[],
        'Tuesday':[],
        'Wednesday':[],
        'Thursday':[],
        'Friday':[],
        'Saturday':[],
        'Sunday':[],
    }   
    month_lengh =  calendar.monthrange(year,month)[1]+1
    for x in range(1,month_lengh):
        day = int_to_day_in_week[date(year, month, x).weekday()]
        days_in_week[day].append(x)
    day = None
    if iteration is 'last':
        day = days_in_week[day_in_week][len(days_in_week[day_in_week])-1]
    elif iteration is 'teenth':
        teenths = [13,14,15,16,17,18,19]
        for teenth in teenths:
            if teenth in days_in_week[day_in_week]:
                day = teenth
    else:
         day = days_in_week[day_in_week][int(iteration[:-2])-1]
    return date(year, month, day)
