import datetime

def meetup_day(year, month, weekday, weekType):

    calendar = {'1st': range(1,8), '2nd': range(8,15), 'teenth': range(13,20), '3rd': range(15,22), '4th': range(22,29), 'last': range(29,32)}

    for calendarDate in calendar[weekType]:

        if datetime.date(year,month,calendarDate).strftime("%A") == weekday:

            return datetime.date(year, month, calendarDate)
 
