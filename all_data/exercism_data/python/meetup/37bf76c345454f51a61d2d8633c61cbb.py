from datetime import date
from calendar import monthrange

def meetup_day(year, month, day_of_week, week_number):
    
    (day_of_week_int_1st, days_in_month) = monthrange(year, month)
    day_of_week_dict = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    day_of_week_int = day_of_week_dict[day_of_week]

    day = 1 + (day_of_week_int - day_of_week_int_1st) + 7 * (day_of_week_int < day_of_week_int_1st)  # first day_of_week in month, 1 to 7

    if week_number == '2nd':
        day += 7
    elif week_number == '3rd':
        day += 14
    elif week_number == '4th':
        day += 21
    elif week_number == 'last':
        if (day + 28) <= days_in_month:
            day += 28
        else:
            day += 21
    elif week_number == 'teenth':  # 13 to 19
        if day <= 5:
            day += 14
        else:
            day += 7

    meetup_date = date(year, month, day)
    return meetup_date
