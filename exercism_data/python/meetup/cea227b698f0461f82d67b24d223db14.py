__author__ = "Greg"

import calendar, datetime
days_of_week = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 
                'Friday': 4, 'Saturday': 5,  'Sunday': 6}

week_indices = {'1st': 0, '2nd': 1, '3rd': 2, '4th': 3, 'last': -1} 

def meetup_day(year, month, day_of_week, day_occurrence):
    """
    determines the date of the nth day of the week in a month, incl. 'last'
    and 'teenth' (1st with a value >= 10 and < 20)
    """
    
    cal = calendar.monthcalendar(year, month)
    day_of_week_index = days_of_week[day_of_week]
    
    not_teenth = day_occurrence != 'teenth'
    day_is_in_first_week = cal[0][day_of_week_index] != 0
    
    if not_teenth and day_is_in_first_week:
        week_index = week_indices[day_occurrence]
    
    elif not_teenth and not day_is_in_first_week:
        week_index = week_indices[day_occurrence] + 1
    
    else:
        for i in range(len(cal)):
            if cal[i][day_of_week_index] >= 10:
                week_index = i
                break

    date = cal[week_index][day_of_week_index]
    return datetime.date(year, month, date)
