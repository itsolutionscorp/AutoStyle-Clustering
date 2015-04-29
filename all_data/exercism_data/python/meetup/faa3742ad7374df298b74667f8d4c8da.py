from datetime import date, timedelta

weekdays = {
    "Monday": 0, 
    "Tuesday": 1, 
    "Wednesday": 2, 
    "Thursday": 3,
    "Friday": 4, 
    "Saturday": 5, 
    "Sunday": 6
}
days_in_month = {
    1: 31, 2: 29, 3: 31,
    4: 30, 5: 31, 6: 30,
    7: 31, 8: 31, 9: 30,
    10: 31, 11: 30, 12: 31  
}
when_to_stop = {
    '1st': 1,
    '2nd': 2,
    '3rd': 3,
    '4th': 4
}

def meetup_day(year, month, dayOfWeek, week):
    meetDay = 1
    occurence = 0

    if week == "teenth":
        for day in range(13, 20):
            if date(year, month, day).weekday() == weekdays[dayOfWeek]:
                return date(year, month, day)
    else:
        for day in range(1, days_in_month[month]+1):
            if date(year, month, day).weekday() == weekdays[dayOfWeek]:
                occurence += 1
                meetDay = day
            elif week != "last":
                if when_to_stop[week] == occurence:
                    return date(year, month, meetDay)

        return date(year, month, meetDay)
