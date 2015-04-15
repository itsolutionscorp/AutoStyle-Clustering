import calendar
from datetime import date

def meetup_day(year,month,week_day_name,wild_card):
    
    WEEK_DAY_NAME = {"Monday":0,"Tuesday":1,"Wednesday":2,"Thursday":3,"Friday":4,"Saturday":5,"Sunday":6}
    
    day_week_name_list = []
    
    for day,week_day in calendar.Calendar().itermonthdays2(year,month):
        if day > 0 and week_day == WEEK_DAY_NAME[week_day_name]:
            day_week_name_list.append(day)
            
    if wild_card == "1st":
        return date(year, month, day_week_name_list[0])
    if wild_card == "2nd":
        return date(year, month, day_week_name_list[1])
    if wild_card == "3rd":
        return date(year, month, day_week_name_list[2])
    if wild_card == "4th":
        return date(year, month, day_week_name_list[3])
    if wild_card == "last":
        return date(year, month, day_week_name_list[-1])
    if wild_card == "teenth":
        for item in day_week_name_list:
            if item in [13,14,15,16,17,19]:
                return date(year, month, item)
