from datetime import date
from calendar import monthrange

def meetup_day(year, month, weekday, meetup):
    weekdays = ["Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"]
    day = weekdays.index(weekday)
    meetingDates = []

    # Go through all dates in the month, collect correct days
    # in  a list and pick the "teenth" day in a separate variable 
    for i in range(1, monthrange(year, month)[1] + 1):
        meetingDate = date(year, month, i)
        if meetingDate.weekday() == day:
            meetingDates.append(meetingDate)
            if (i > 12 and i < 20):
                teenth = meetingDate
    # Return the asked date
    if meetup == "1st":
        return(meetingDates[0])
    if meetup == "2nd":
        return(meetingDates[1])
    if meetup == "3rd":
        return(meetingDates[2])
    if meetup == "4th":
        return(meetingDates[3])
    if meetup == "last":
        return(meetingDates[-1])
    if meetup == "teenth":
        return(teenth)
    
