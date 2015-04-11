import datetime as dt

daysName = {
    'Monday' : 0,
    'Tuesday' : 1,
    'Wednesday' : 2,
    'Thursday' : 3,
    'Friday' : 4,
    'Saturday' : 5,
    'Sunday' : 6
}

def meetup_day(year, month, dayOfWeek, occurence):
    if occurence == 'last':
        #Start with finding the last day of the month
        #Could also use last_day_of_month
        lastDayMonth = dt.date(year, month + 1, 1) + dt.timedelta(days = -1)
        #Get the number of the day from the last day
        lastDay = dt.date.weekday(lastDayMonth)
        #Get the number of the day from the parameter
        dayNumber = daysName[dayOfWeek]

        #Calculate the values that need to be subtracted from the last day of the month
        incValue =  dayNumber - lastDay
        #If desired day is "after" the last day of the month day then subtract 7
        #e.g.: desired day (in parameter) is Tuesday and last day is Monday, incValue initially is 1.
        #Subtract 7 gets -6 which can be used in the timedelta
        if incValue > 0:
            incValue = incValue - 7

        returnDate = lastDayMonth + dt.timedelta(days = incValue)

    elif occurence == 'teenth':
        #Similar to 'last'
        #Difference: start with 13th and add incValue rather than subtract
        firstDay = dt.date.weekday(dt.date(year, month, 13))
        dayNumber = daysName[dayOfWeek]

        incValue = dayNumber - firstDay
        if incValue < 0:
            incValue = incValue + 7

        returnDate = dt.date(year, month, 13 + incValue)

    else:
        #Similar to teenth
        #Difference start with 1st day of the month
        firstDay = dt.date.weekday(dt.date(year, month, 1))
        dayNumber = daysName[dayOfWeek]

        incValue = dayNumber - firstDay
        if incValue < 0:
            incValue = incValue + 7

        occInt = int(occurence[0])

        incValue += 7 * (occInt - 1)

        returnDate = dt.date(year, month, 1 + incValue)

    return returnDate

if __name__ == '__main__':
    print(meetup_day(2013, 2, 'Saturday', 'teenth'))
