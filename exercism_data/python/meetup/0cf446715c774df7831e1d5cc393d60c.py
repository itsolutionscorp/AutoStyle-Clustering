from calendar import monthrange
import datetime

teenths = [13, 14, 15, 16, 17, 18, 19]
days_dict = {
    0: "Monday",
    1: "Tuesday",
    2: "Wednesday",
    3: "Thursday",
    4: "Friday",
    5: "Saturday",
    6: "Sunday",
}

def meetup_day(year, month, day, type):
    monthlength = monthrange(year, month)[-1]

    if type == "teenth":
        for date in teenths:
            if day == days_dict[datetime.date(year, month, date).weekday()]:
                return datetime.date(year, month, date)
    
    if type == "last":
        for date in range(monthlength, 0, -1):
            if day == days_dict[datetime.date(year, month, date).weekday()]:
                return datetime.date(year, month, date)

    count = 0
    for date in range(1, monthlength+1):
        if day == days_dict[datetime.date(year, month, date).weekday()]:
            count += 1
            if type == "1st":
                return datetime.date(year, month, date)
            if type == "2nd":
                if count == 2:
                    return datetime.date(year, month, date)
            if type == "3rd":
                if count == 3:
                    return datetime.date(year, month, date)
            if type == "4th":
                if count == 4:
                    return datetime.date(year, month, date)
