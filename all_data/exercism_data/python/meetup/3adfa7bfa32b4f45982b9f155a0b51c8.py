from datetime import date, timedelta

class MeetupDayException(Exception):
    pass
def meetup_day(year, month, day, nr):
    tempdate = date(year, month, 1)
    weekdays = {"Monday" : 0, "Tuesday" : 1, "Wednesday" : 2, "Thursday" : 3, "Friday" : 4, "Saturday" : 5, "Sunday" : 6}
    day = weekdays[day]
    if nr == "teenth":
        check = False
        tempdate = date(year, month, 13)
        for k in range(7):
            if(tempdate.weekday() == day):
                check = True
                break
            else:
                tempdate += timedelta(1)
        if check == True:
            return tempdate
    elif nr == "last":
        tempdate = date(year, month, 21)
        returned = 0
        while tempdate.month == month:
            if(tempdate.weekday() == day):
                returned = tempdate
                tempdate += timedelta(1)
            else:
                tempdate += timedelta(1)
        return returned
    else:
        try:
            times = int(nr[0])
            counter = 0
            returned = 0
            while tempdate.month == month:
                if tempdate.weekday() == day:
                    counter += 1
                    if counter == times:
                        returned = tempdate
                        break
                    tempdate += timedelta(1)
                else:
                    tempdate += timedelta(1)
            if returned == 0:
                raise MeetupDayException
            else:
                return returned
        except MeetupDayException:
            pass
