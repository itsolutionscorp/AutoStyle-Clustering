from datetime import date
import calendar

def meetup_day(year, month, day, qualifier):
    '''
    Calculate the date of meetup day
    :param year:
    :param month:
    :param day:
    :param qualifier:
    :return:
    '''
    # offset lookup
    week_offset = {'1st':0,'2nd':1,'3rd':2,'4th':3,'5th':4}
    day_offset = {'Monday':0,'Tuesday':1,'Wednesday':2,'Thursday':3,'Friday':4,'Saturday':5,'Sunday':6}

    if qualifier == 'last':
        numdays_in_month = calendar.monthrange(year, month)[1]
        lastday = date(year,month,numdays_in_month).weekday()
        dt = numdays_in_month - (day_offset[day] - (7+lastday))%7
    elif qualifier == 'teenth':
        start_teenthday = date(year,month,13).weekday()
        dt = 13 + (day_offset[day] + (7 - start_teenthday)) % 7
        print start_teenthday, dt
    else:
        #calculate weekday on 1st
        firstday = date(year,month,1).weekday()
        dt = (week_offset[qualifier]*7+1) + (day_offset[day] + (7-firstday))%7

    return date(year,month,dt)

#print (meetup_day(2015, 3, 'Tuesday', '1st')) #03
#print(meetup_day(2015, 2, 'Sunday', '4th')) #22
#print (meetup_day(2013, 4, 'Monday', '2nd')) #08
#print (meetup_day(2013, 10, 'Thursday', 'last')) #31
#print (meetup_day(2013, 2, 'Saturday', 'teenth')) #16
