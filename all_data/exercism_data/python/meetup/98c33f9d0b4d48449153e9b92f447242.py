#--------------------------------------------
# Name:        McFace3000
# Purpose:      Ummm...what?
#--------------------------------------------
import datetime
import string
import calendar

def meetup_day(year, month, weekday, fancy):
    database = calendar.monthcalendar(year, month)
    new_fancy = 0
    Days = ["Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"]
    teenth = [13, 14, 15, 16, 17, 18, 19]

    #My searchable list, where I have the individual days of the week under each day, as a list
    Wonky = {'Monday': [], 'Tuesday': [], 'Wednesday': [], 'Thursday': [], 'Friday': [], 'Saturday': [], 'Sunday': []}
    #Creating a list under Wonky, which is a dictionary.  I want to turn this into a FOR loop, and have it
    #go over every item
    count = 0
    while count != len(database):
        for i in Days:
            if database[count][Days.index(i)] > 0:
                Wonky[i].append(database[count][Days.index(i)])
        count += 1
    if fancy == 'teenth':
        for i in Wonky[weekday]:
            if i in teenth:
                new_fancy = i
        return datetime.date(year, month, new_fancy)
    if fancy == 'last':
        new_fancy = -1
    else:
        new_fancy = ''.join(i for i in fancy if i.isdigit())
        new_fancy = int(new_fancy) -1
    return datetime.date(year, month, Wonky[weekday][new_fancy])
