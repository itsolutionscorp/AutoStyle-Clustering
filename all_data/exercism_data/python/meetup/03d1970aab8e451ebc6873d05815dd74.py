from datetime import date 
import calendar

def meetup_day(yr, mm, dayOfWeek, ordinal):
    ordinalMap = {'1st':0, '2nd':1, '3rd':2, '4th':3, '5th':4}
    dayMap = {"Monday":0, "Tuesday":1, "Wednesday":2, "Thursday":3, "Friday": 4, "Saturday": 5, "Sunday": 6}
    weekMatrix = calendar.monthcalendar(yr, mm)
    monthRange = calendar.monthrange(yr, mm)
    '''Monday is 0 and Sat is 6'''
    firstWeekdayOfMonth = monthRange[0]
    ThisIsTheWeek = []

    if (ordinal == 'teenth'):
        for i in range(13, 19):
            if calendar.day_name[calendar.weekday(yr, mm, i)] == dayOfWeek:
                return date(yr, mm, i)
    if ordinal == '1st' or ordinal == '2nd' or ordinal == '3rd' or ordinal == '4th' or ordinal == '5th':
        #start count the week if dayOfWeek >= the first dayOfWeek found
        try:
            if dayMap[dayOfWeek] < firstWeekdayOfMonth:
                offset = ordinalMap[ordinal] + 1
                if offset > len(weekMatrix) - 1:
                    raise MeetupDayException(Exception)
                ThisIsTheWeek = weekMatrix[offset]
            else:
                offset = ordinalMap[ordinal]
                if offset > len(weekMatrix):
                    raise MeetupDayException(Exception)
                ThisIsTheWeek = weekMatrix[offset] 
            for day in ThisIsTheWeek:
                if calendar.day_name[calendar.weekday(yr, mm, day)] == dayOfWeek:
                    return date(yr, mm, day)
        except MeetupDayException(Exception):
            print ("")
    if ordinal == 'last':
        ThisIsTheWeek = weekMatrix[len(weekMatrix) - 1]
        for day in ThisIsTheWeek:
            if calendar.day_name[calendar.weekday(yr, mm, day)] == dayOfWeek:
                return date(yr, mm, day)
    
class MeetupDayException(Exception):
    pass
