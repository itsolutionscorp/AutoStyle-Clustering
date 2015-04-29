import datetime
import calendar

weekday_index = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 
                     'Friday': 4, 'Saturday': 5, 'Sunday': 6}

def meetup_day(year, month, weekday, number):
    first_day = calendar.monthrange(year, month)
    find_index = weekday_index[weekday]

    if number[0] != 't' and number[0] != 'l':
        goto = int(number[0])
        for i in range(1, first_day[1]+1):
            this_day = datetime.date(year, month, i)
            this_weekday = this_day.weekday()
            
            if this_weekday == find_index:
                goto = goto - 1
                
            if goto == 0:
                return datetime.date(year, month, i)

    elif number[0] == 't':
        for i in range(13, 20):
            this_day = datetime.date(year, month, i)
            this_weekday = this_day.weekday()
            
            if this_weekday == find_index:
                return datetime.date(year, month, i)
    else:
         for i in range(first_day[1], 0, -1):
             this_day = datetime.date(year, month, i)
             this_weekday = this_day.weekday()
             
             if this_weekday == find_index:
                 return datetime.date(year, month, i)
