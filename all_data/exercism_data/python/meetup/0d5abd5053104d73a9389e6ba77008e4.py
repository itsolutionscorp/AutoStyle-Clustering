from datetime import date
from datetime import *

def meetup_day(year, month, day_of_week, which_day):
    if (day_of_week.lower() != "monday" and day_of_week.lower() != "tuesday" and day_of_week.lower() != "wednesday" and
    day_of_week.lower() != "thursday" and day_of_week.lower() != "friday" and day_of_week.lower() != "saturday" and
    day_of_week.lower() != "sunday"):
        return "You must input a valid day of the week!"
    if (which_day != "teenth" and which_day != "1st" and which_day != "2nd" and
    which_day != "3rd" and which_day != "4th" and which_day != "last"):
        return "You must input a valid specification for which day!"
    check=True
    try_date=1
    try:
        test=date(year, month, try_date)
    except:
        return "You must input a valid date!"

    if day_of_week.lower()=="monday":
        dow=0
    elif day_of_week.lower()=="tuesday":
        dow=1
    elif day_of_week.lower()=="wednesday":
        dow=2
    elif day_of_week.lower()=="thursday":
        dow=3
    elif day_of_week.lower()=="friday":
        dow=4
    elif day_of_week.lower()=="saturday":
        dow=5
    elif day_of_week.lower()=="sunday":
        dow=6

    
    if which_day == "1st":
        counter=1
        teenth=False
    elif which_day == "2nd":
        counter=2
        teenth=False
    elif which_day == "3rd":
        counter=3
        teenth=False
    elif which_day == "4th":
        counter=4
        teenth=False
    elif which_day == "last":
        counter=100
        teenth=False
    elif which_day == "teenth":
        counter=6
        try_date=13
        teenth=True
    while counter>0:
        try:
            test=date(year, month, try_date)
        except:
            return return_date
        if test.weekday() == dow:
            return_date=test
            counter=counter-1
        if teenth==True:
            counter=counter-1
        try_date=try_date+1
    return return_date

        
            
            
                
