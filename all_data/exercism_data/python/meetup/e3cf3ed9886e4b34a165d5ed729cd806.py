from datetime import date

def meetup_day(year, month, weekday, offset):
    isodays = {"Monday":1, "Tuesday":2, "Wednesday":3, "Thursday":4, "Friday":5, "Saturday":6, "Sunday":7}
    first_day = date(year, month, 1).isoweekday()
    dist = isodays[weekday] - first_day
    if dist < 0:
        dist += 7
    if offset == "1st":
        d = 1+dist
    if offset == "2nd":
        d = 8+dist
    if offset == "3rd":
        d = 15+dist
    if offset == "4th":
        d = 22+dist
    if offset == "last":
        d = 29+dist
    if offset == "teenth":
        if dist > 4:
            d = 8+dist
        else:
            d = 15+dist
    return(date(year, month, d))
