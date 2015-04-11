from datetime import *
from calendar import monthrange

def meetup_day(year_arg,month_arg,weekday_arg,pos_arg):
    weekday_arg_dict = {
        "Monday" : 0,
        "Tuesday" : 1,
        "Wednesday" : 2,
        "Thursday" : 3,
        "Friday" : 4,
        "Saturday" : 5,
        "Sunday" : 6,
        }
        
    pos_arg_dict = {
        "1st" : 0,
        "2nd" : 1,
        "3rd" : 2,
        "4th" : 3,
        "last" : 9,
        "teenth" : 10,
        }
        
    weekday_arg_num = weekday_arg_dict[weekday_arg]
    pos_arg_num = pos_arg_dict[pos_arg]
    month_length = monthrange(year_arg,month_arg)[1]
    days = []
    meetup_day = 0
    for x in range(1,month_length+1):
        if date(year_arg,month_arg,x).weekday() == weekday_arg_num:
            days.append(x)
    if pos_arg_num == 10:
        for y in days:
            if y in range(13,20):
                meetup_day = y
    elif pos_arg_num == 9:
        meetup_day = days[len(days)-1]
    else:
        meetup_day = days[pos_arg_num]
    return date(year_arg,month_arg,meetup_day)
