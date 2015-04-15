import datetime
import calendar

def meetup_day(year, month, dow, number):
    counter = 0
    for day in range(1, calendar.monthrange(year, month)[1]+1):
        if dow ==  datetime.date(year, month, day).strftime('%A'):
            counter += 1

            if number == 'teenth':
                 if day in (13, 14, 15, 16, 17, 18, 19):
                     return datetime.date(year, month, day)
            elif number == 'last':
               # print(calendar.monthrange(year, month)[1] - day)
                if (calendar.monthrange(year, month)[1] - day) < 7:
                    return datetime.date(year, month, day)

        if number == '1st':
            if counter == 1:
                return datetime.date(year, month, day)
        elif number == '2nd':
            if counter == 2:
                return datetime.date(year, month, day)
        elif number == '3rd':
            if counter == 3:
                return datetime.date(year, month, day)
        elif number == '4th':
            if counter == 4:
                return datetime.date(year, month, day)

    
