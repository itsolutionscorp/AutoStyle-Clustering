import calendar
from datetime import date

def meetup_day(year, month, day_str, num_str):
    
    cal = calendar.Calendar()
    
    day_index = ["Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"].index(day_str)
    
    extract = [day for day, week_day
        in cal.itermonthdays2(year, month)
        if week_day == day_index
        and day > 0]
    
    if num_str == "teenth":
        for day in extract:
            if day > 12 and day < 20:
                return date(year, month, day)
    
    if num_str == "last":
        return date(year, month, extract[-1])
    
    num_index = ["1st", "2nd", "3rd", "4th"].index(num_str)
    
    return date(year, month, extract[num_index])
        
        
