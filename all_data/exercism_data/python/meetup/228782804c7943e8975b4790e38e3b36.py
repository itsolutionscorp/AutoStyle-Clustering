from datetime import date
from calendar import monthrange

def meetup_day (year, month, WD, W):
    weeks = {"1st":0,"2nd":1,"3rd":2,"4th":3,"last":5,"teenth":6}
    weekdays = {"Monday":0,"Tuesday":1,"Wednesday":2,"Thursday":3,"Friday":4,"Saturday":5,"Sunday":6}
    weekc = weeks[W]       
    weekdayc = weekdays[WD]
    
    if weekc < 4:
        relitive = date(year,month,1).weekday()
        if relitive > weekdayc:
            return date(year,month,7*weekc + abs(7-relitive)+weekdayc+1)
        else:
            return date(year,month,7*weekc + abs(relitive - weekdayc)+1)
    elif weekc == 5:
        numdays = monthrange(year, month) 
        relitive = date(year,month,numdays[1]).weekday()
        if relitive >= weekdayc:
            return date(year,month,numdays[1] - abs(weekdayc - relitive))
        else:
            return date(year,month,numdays[1] - (abs(7 - weekdayc) + relitive))
    elif weekc == 6:
        relitive = date(year,month,13).weekday()
        if relitive > weekdayc:
            return date(year,month,13 + abs(7-relitive)+weekdayc)
        else:
            return date(year,month,13 + abs(relitive - weekdayc))
    else:
        return 0
        
    
