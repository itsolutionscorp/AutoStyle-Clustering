import datetime
import calendar

def meetup_day(year, month, day_of_week, occurrence):
    result_day = 0
    weekdays = ["Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"]
    first_day_of_month = datetime.date(year, month, 1)
    occurrence = occurrence.lower()
    last_day_of_month = calendar.monthrange(year, month)[1]
    if occurrence == "1st":
        day_range = range(1, 8)
    elif occurrence == "2nd":
        day_range = range(8, 15)
    elif occurrence == "3rd":
        day_range = range(15, 22)
    elif occurrence == "4th":
        day_range = range(22, 29)
    elif occurrence == "teenth":
        day_range = range(13, 20)
    elif occurrence == "last":
        day_range = range(last_day_of_month, 21, -1)
    else:
        return None

    for day in day_range:
        if day_of_week == weekdays[datetime.date(year, month, day).weekday()]:
            result_day = day
            break
    return datetime.date(year, month, result_day)



