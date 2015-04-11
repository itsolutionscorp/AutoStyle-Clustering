from datetime import date
import datetime

days = {
    "Monday": 1,
    "Tuesday": 2,
    "Wednesday": 3,
    "Thursday": 4,
    "Friday": 5,
    "Saturday": 6,
    "Sunday": 7
}
mods = {
    # Placeholder Teenth
    'teenth': 0,
    'last': 5,
    '1st': 1,
    '2nd': 2,
    '3rd': 3,
    '4th': 4,
}


def meetup_day(year, month, day, mod):
    firstDay = date(year, month, 1).weekday()
    modifier = 0

    # If the month starts after the day
    if firstDay < days[day]:
        modifier = (mods[mod] - 1) * 7
    if firstDay >= days[day]:
        modifier = (mods[mod]) * 7

    print(firstDay)
    indexShift = days[day] - firstDay

    if mod == "teenth":
        # Dirty hackathon code right here
        if 14 + indexShift > 12 and 14 + indexShift < 20:
            modifier = 14

    modded_day = indexShift + modifier

    # return(modded_day)
    return datetime.date(year, month, modded_day)
