from datetime import date
import calendar

def meetup_day(year, month, day, ending):
    focus_month = calendar.monthcalendar(year, month)
    weekdays = {"Monday":    0,
                "Tuesday":   1, 
                "Wednesday": 2, 
                "Thursday":  3,
                "Friday":    4, 
                "Saturday":  5, 
                "Sunday":    6,}

    days = []

    for i in range(len(focus_month)):
        if focus_month[i][weekdays[day]]:
            days.append(focus_month[i][weekdays[day]])
    
    if ending == "1st" or ending == "first":
        return date(year, month, days[0])
    elif ending == "2nd":
        return date(year, month, days[1])
    elif ending == "3rd":
        return date(year, month, days[2])
    elif ending == "4th":
        return date(year, month, days[3])
    elif ending == "last":
        return date(year, month, days[-1])
    elif ending == "teenth":
        if days[1] >= 12:
            return date(year, month, days[1])
        else: 
            return date(year, month, days[2])
