#meetup.py

import calendar
from datetime import date

def meetup_day(year, month, weekday, place):
    """
        takes 4 arguments:
        int <year>
        int <month>
        string <day of the week>
        string <position of day in the month>
        (i.e '1st', '2nd', '3rd', '4th', 'teenth', 'last')
    """
    
    # initialize weekdays
    weekDict = {"monday" : 0,\
                "tuesday" : 1,\
		"wednesday" : 2,\
                "thursday" : 3,\
		"friday" : 4,\
                "saturday" : 5,\
		"sunday" : 6} # end of weekDict
    
    # create calendar
    c = calendar.monthcalendar(year, month)
    day = None
    
    # choose a proper week index range
    # i.e. if the first day of the month begins at an
    # index other than zero.
    if not c[0][weekDict[weekday.lower()]]:
        weekRange = (1,5)
        numRange = [1, 2, 3, 4]
    else:
        weekRange = (0,4)
        numRange = [0, 1, 2, 3]

    for i in range(weekRange[0], weekRange[1]):
        if place.lower() == "1st":
            day = c[numRange[0]][weekDict[weekday.lower()]]
        if place.lower() == "2nd":
            day = c[numRange[1]][weekDict[weekday.lower()]]
        if place.lower() == "3rd":
            day = c[numRange[2]][weekDict[weekday.lower()]]
        if place.lower() == "4th":
            day = c[numRange[3]][weekDict[weekday.lower()]]
        if place.lower() == "last":
            day = c[-1][weekDict[weekday.lower()]]
        if place.lower() == "teenth":
            if c[i][weekDict[weekday.lower()]] >= 13 and\
               c[i][weekDict[weekday.lower()]] <= 19:
                day = c[i][weekDict[weekday.lower()]]
                
    # return date
    return date(year, month, day)
