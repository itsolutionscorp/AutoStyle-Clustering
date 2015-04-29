from datetime import date, timedelta

def meetup_day(onYear, onMonth, onWeekDay, onType):
    count = 0
    last = None
    
    current = date(onYear, onMonth, 1)
    while(current.month == onMonth):
        if(current.strftime("%A") == onWeekDay):
            count += 1
            last = current
            
            if str(count) == onType[0]:
                return current
            
            if onType == 'teenth' and current.day >= 13:
                return current
            
        current += timedelta(days=1)
    
    return last if onType == 'last' else None
