import calendar
import datetime

# tried doing it in an iterative way that was somewhat naive about the structure of a month
# it's pretty ugly...

def nthday(cal, ordinal, day):
    for (x, y) in cal:
        if y == day and x > 0:
            lastseen = x
            if ordinal > 0:
               ordinal -= 1
            else:
                return x
    # if we drove off the end of the calendar...
    return lastseen

def teenthday(cal, day):
    for (x, y) in cal:
        if y == day and x >= 13 and x <=19:
            return x

def meetup_day(y,m,d,o):

    weekdays = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3, "Friday": 4, "Saturday": 5, "Sunday": 6}
    ordinals = {"1st": 0, "2nd": 1, "3rd": 2, "4th": 3,  "last": 4}
    cal = calendar.Calendar(calendar.SUNDAY).itermonthdays2(y,m)

    if o == "teenth":
        weekday = teenthday(cal, weekdays[d])
    else:
        ordinal = ordinals[o]
        weekday = nthday(cal, ordinal, weekdays[d])

    return datetime.date(y,m,weekday)

if __name__ == "__main__":
    print(meetup_day(2013,9,"Thursday","3rd"))
