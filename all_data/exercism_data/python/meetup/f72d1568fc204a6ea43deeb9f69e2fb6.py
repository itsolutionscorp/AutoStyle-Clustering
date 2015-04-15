from calendar import weekday, day_name
from datetime import date

def meetup_day(year, month, day_of_week, day_desc):
    
    caldays = day_name[0:7];
    
    if day_desc == "1st":
        interval = [n for n in range(1, 8)];
    elif day_desc == "2nd":
        interval = [n for n in range(8, 15)];
    elif day_desc == "3rd":
        interval = [n for n in range(15, 22)];
    elif day_desc == "teenth":
        interval = [n for n in range(13, 20)];
    elif day_desc == "4th":
        interval = [n for n in range(22, 29)];
    else:
        interval = [n for n in range(29, 32)];

    for i in interval:
        if weekday(year, month, i) == caldays.index(day_of_week):
            return date(year, month, i);
        

