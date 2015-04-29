from datetime import datetime
import calendar

class MeetupDayException(Exception):
    pass

def meetup_day(year, month, dayOfWeek, qualifier):
    firstDayOfMonth = datetime(year, month, 1).weekday()

    dayIndex ={'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}
    qualifierRange={'1st':range(0,1), '2nd':range(1,2), '3rd':range(2,3), '4th':range(3,4), '5th':range(4,5), 'teenth':range(1,3), 'last':range(3,5)}

    calendar.setfirstweekday(calendar.weekday(year, month, 1))
    weeks = calendar.monthcalendar(year, month)

    firstDayAndDays = calendar.monthrange(year, month)
    offset = dayIndex[dayOfWeek] - firstDayOfMonth
    if offset < 0:
            offset = offset + 7

    selectedMeetupDay = 0;
    for num in qualifierRange[qualifier]:
        if num < len(weeks):
            week = weeks[num]
            if week[offset] > 0:
                if qualifier == 'teenth' and week[offset]>12 and week[offset]<20:
                    selectedMeetupDay = week[offset]
                    break
                else:
                    selectedMeetupDay = week[offset]
    if selectedMeetupDay == 0:
        raise MeetupDayException('No meetup day found for the criterion year={}, month={}, day={}, qualifier={}'.format(year, month, dayOfWeek,qualifier))
    return datetime(year, month, selectedMeetupDay).date()
