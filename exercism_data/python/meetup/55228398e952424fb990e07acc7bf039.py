from calendar import monthcalendar
import datetime

def meetup_day(year, month, day, week):
    day_to_digit = {'Monday':0, 'Tuesday':1,
                    'Wednesday':2,'Thursday':3,
                    'Friday':4, 'Saturday':5,
                    'Sunday':6}

    week_name_to_number =  {'1st':0, '2nd':1, '3rd':2,
                            '4th':3, 'last':-1}
    
    #create list of weeks in month [[0,0,1,2,3,4,5], [6,7,8,9,10,11,12], ...]
    month_cal = monthcalendar(year, month)

    #create list of just one day of month
    weekdays = []
    for weeks in month_cal:
        weekdays.append(weeks[day_to_digit[day]])
    
    #remove any zeros
    if weekdays.count(0) > 0:
        weekdays.remove(0)

    #find day in list of days
    if week == 'teenth':
        if 13 <= weekdays[1] <= 19:
            final_day = weekdays[1]
        else:
            final_day = weekdays[2]
    else:
        final_day = weekdays[week_name_to_number[week]]
    
    return datetime.date(year, month, final_day)
