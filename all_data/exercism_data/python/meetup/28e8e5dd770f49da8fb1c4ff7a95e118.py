import calendar
import datetime

def meetup_day(year, month, day_name, week_id):
    
    YearNum = year
    MonthNum = month
    #DayName = day_name
    WeekId = week_id

    DayNum = list(calendar.day_name).index(day_name)
    # for some reason if I use anything other than "day_name" the program fails - see line 8
    
    WhichDay = []
    for i in list(zip(*calendar.monthcalendar(YearNum, MonthNum)))[DayNum]:
        if i != 0:
            WhichDay.append(i)

    if WeekId == 'teenth':
        def GoToNext():
            for i in WhichDay:
                if i in range(13, 20):
                    yield i
        NewDay = next(GoToNext(), None)
    else:
        if WeekId == 'last':
            WeekChange = -1
        else:
            WeekChange = int(WeekId[0]) - 1
        NewDay = WhichDay[WeekChange]

    return datetime.date(YearNum, MonthNum, NewDay)
