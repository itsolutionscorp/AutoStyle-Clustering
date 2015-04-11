__author__ = 'Dalton'
import calendar
from datetime import date


def meetup_day(year, month, day, which):
    cal = calendar.monthcalendar(year, month)

    if which == '1st':                              #first ______
        if day == 'Monday':
            if cal[0][0] == 0:
                return date(year, month, cal[1][0])
            else:
                return date(year, month, cal[0][0])
        elif day == 'Tuesday':
            if cal[0][1] == 0:
                return date(year, month, cal[1][1])
            else:
                return date(year, month, cal[0][1])
        elif day == 'Wednesday':
            if cal[0][2] == 0:
                return date(year, month, cal[1][2])
            else:
                return date(year, month, cal[0][2])
        elif day == 'Thursday':
            if cal[0][3] == 0:
                return date(year, month, cal[1][3])
            else:
                return date(year, month, cal[0][3])
        elif day == 'Friday':
            if cal[0][4] == 0:
                return date(year, month, cal[1][4])
            else:
                return date(year, month, cal[0][4])
        elif day == 'Saturday':
            if cal[0][5] == 0:
                return date(year, month, cal[1][5])
            else:
                return date(year, month, cal[1][5])
        elif day == 'Sunday':
            if cal[0][6] == 0:
                return date(year, month, cal[1][6])
            else:
                return date(year, month, cal[0][6])
    
    if which == 'last':                                 #last ________
        if day == 'Monday':
            if cal[-1][0] == 0:
                return date(year, month, cal[-2][0])
            else:
                return date(year, month, cal[-1][0])
        elif day == 'Tuesday':
            if cal[-1][1] == 0:
                return date(year, month, cal[-2][1])
            else:
                return date(year, month, cal[-1][1])
        elif day == 'Wednesday':
            if cal[-1][2] == 0:
                return date(year, month, cal[-2][2])
            else:
                return date(year, month, cal[-1][2])
        elif day == 'Thursday':
            if cal[-1][3] == 0:
                return date(year, month, cal[-2][3])
            else:
                return date(year, month, cal[-1][3])
        elif day == 'Friday':
            if cal[-1][4] == 0:
                return date(year, month, cal[-2][4])
            else:
                return date(year, month, cal[-1][4])
        elif day == 'Saturday':
            if cal[-1][5] == 0:
                return date(year, month, cal[-2][5])
            else:
                return date(year, month, cal[-2][5])
        elif day == 'Sunday':
            if cal[-1][6] == 0:
                return date(year, month, cal[-2][6])
            else:
                return date(year, month, cal[-1][6])
    
    if which == '2nd':                                  #2nd __________
        if day == 'Monday':
            if cal[0][0] == 0:
                return date(year, month, cal[2][0])
            else:
                return date(year, month, cal[1][0])
        elif day == 'Tuesday':
            if cal[0][1] == 0:
                return date(year, month, cal[2][1])
            else:
                return date(year, month, cal[1][1])
        if day == 'Wednesday':
            if cal[0][2] == 0:
                return date(year, month, cal[2][2])
            else:
                return date(year, month, cal[1][2])
        if day == 'Thursday':
            if cal[0][3] == 0:
                return date(year, month, cal[2][3])
            else:
                return date(year, month, cal[1][3])
        if day == 'Friday':
            if cal[0][4] == 0:
                return date(year, month, cal[2][3])
            else:
                return date(year, month, cal[1][3])
        if day == 'Saturday':
            if cal[0][5] == 0:
                return date(year, month, cal[2][5])
            else:
                return date(year, month, cal[1][5])
        if day == 'Sunday':
            if cal[0][6] == 0:
                return date(year, month, cal[2][6])
            else:
                return date(year, month, cal[1][6])
    

    if which == '3rd':                                  #3rd ___________
        if day == 'Monday':
            if cal[0][0] == 0:
                return date(year, month, cal[3][0])
            else:
                return date(year, month, cal[2][0])
        elif day == 'Tuesday':
            if cal[0][1] == 0:
                return date(year, month, cal[3][1])
            else:
                return date(year, month, cal[2][1])
        if day == 'Wednesday':
            if cal[0][2] == 0:
                return date(year, month, cal[3][2])
            else:
                return date(year, month, cal[2][2])
        if day == 'Thursday':
            if cal[0][3] == 0:
                return date(year, month, cal[3][3])
            else:
                return date(year, month, cal[2][3])
        if day == 'Friday':
            if cal[0][4] == 0:
                return date(year, month, cal[3][3])
            else:
                return date(year, month, cal[2][3])
        if day == 'Saturday':
            if cal[0][5] == 0:
                return date(year, month, cal[3][5])
            else:
                return date(year, month, cal[2][5])
        if day == 'Sunday':
            if cal[0][6] == 0:
                return date(year, month, cal[3][6])
            else:
                return date(year, month, cal[2][6])
    
    if which == '4th':                              #4th ___________
        if day == 'Monday':
            if cal[0][0] == 0:
                return date(year, month, cal[4][0])
            else:
                return date(year, month, cal[3][0])
        elif day == 'Tuesday':
            if cal[0][1] == 0:
                return date(year, month, cal[4][1])
            else:
                return date(year, month, cal[3][1])
        if day == 'Wednesday':
            if cal[0][2] == 0:
                return date(year, month, cal[4][2])
            else:
                return date(year, month, cal[3][2])
        if day == 'Thursday':
            if cal[0][3] == 0:
                return date(year, month, cal[4][3])
            else:
                return date(year, month, cal[3][3])
        if day == 'Friday':
            if cal[0][4] == 0:
                return date(year, month, cal[4][3])
            else:
                return date(year, month, cal[3][3])
        if day == 'Saturday':
            if cal[0][5] == 0:
                return date(year, month, cal[4][5])
            else:
                return date(year, month, cal[3][5])
        if day == 'Sunday':
            if cal[0][6] == 0:
                return date(year, month, cal[4][6])
            else:
                return date(year, month, cal[3][6])


    if which == 'teenth':                                  #teenth ___________
        if day == 'Monday':
            if cal[0][0] == 0:
                return date(year, month, cal[2][0])
            else:
                return date(year, month, cal[3][0])
        elif day == 'Tuesday':
            if cal[0][1] == 0:
                return date(year, month, cal[2][1])
            else:
                return date(year, month, cal[3][1])
        if day == 'Wednesday':
            if cal[0][2] == 0:
                return date(year, month, cal[2][2])
            else:
                return date(year, month, cal[3][2])
        if day == 'Thursday':
            if cal[0][3] == 0:
                return date(year, month, cal[2][3])
            else:
                return date(year, month, cal[3][3])
        if day == 'Friday':
            if cal[0][4] == 0:
                return date(year, month, cal[1][3])
            else:
                return date(year, month, cal[2][3])
        if day == 'Saturday':
            if cal[0][5] == 0:
                return date(year, month, cal[1][5])
            else:
                return date(year, month, cal[2][5])
        if day == 'Sunday':
            if cal[0][6] == 0:
                return date(year, month, cal[2][6])
            else:
                return date(year, month, cal[3][6])
