from datetime import date
from calendar import monthrange
from collections import OrderedDict as OD


def meetup_day(year, month, weekday, meetup):
    weekdays = ["Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"]
    day = weekdays.index(weekday)
    # Helper list to associate found days to asked meetups
    meetups = ["1st", "2nd", "3rd", "4th", "5th"]
    meetingDates = {}
    nday = 0          # Keep track how many weekdays found

    # Pick the dates matching with asked weekday to dictionary
    for i in range(1, monthrange(year, month)[1] + 1):
        meetingDate = date(year, month, i)
        if meetingDate.weekday() == day:
            meetingDates[meetups[nday]] = meetingDate
            nday += 1
            
            # Update last day
            meetingDates["last"] = meetingDate

            if (i > 12 and i < 20):
                meetingDates["teenth"] = meetingDate

    # Return the asked date
    return(meetingDates[meetup])
