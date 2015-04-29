__author__ = "Greg"

from datetime import date
from calendar import day_name, monthcalendar

DAYS_OF_WEEK = dict(zip(list(day_name), range(7)))  
# c/o @dagbdagb via @Silvester23

WEEK_INDICES = {'1st': 0, 
                '2nd': 1, 
                '3rd': 2, 
                '4th': 3, 
                'last': -1} 

def meetup_day(year, month, day_of_week, day_occurrence):
    """
    determines the date of the nth day of the week in a month, incl. 'last'
    and 'teenth' (1st with a value >= 13 and < 20)
    """
    
    cal = monthcalendar(year, month)
    day_of_week_index = DAYS_OF_WEEK[day_of_week]
    
    not_teenth = day_occurrence != 'teenth'
    day_in_first_week = cal[0][day_of_week_index] != 0
    
    if not_teenth and day_in_first_week:
        week_index = WEEK_INDICES[day_occurrence]
    
    elif not_teenth and not day_in_first_week:
        week_index = WEEK_INDICES[day_occurrence] + 1
    
    else:
        for i in range(len(cal)):
            if cal[i][day_of_week_index] >= 13:
                week_index = i
                break

    meetup_date = cal[week_index][day_of_week_index]
    return date(year, month, meetup_date)
