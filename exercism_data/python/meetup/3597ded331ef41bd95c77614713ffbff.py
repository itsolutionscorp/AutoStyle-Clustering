from datetime import date
from calendar import Calendar

utility_calendar=Calendar()
recurrence_dictionary = {'1st':0, 'first': 0, '2nd':1, 'second':1, '3rd':2, 'third':2, '4th':3, 'fourth':3, '5th':4, 'fifth':4}
days_dictionary = {'Monday':0, 'Tuesday':1, 'Wednesday':2, 'Thursday':3, 'Friday':4, 'Saturday':5, 'Sunday':6}

def meetup_day(year, month, day, recurrence):
    #First, generate two lists that contain all the days and the dates in the given month
    daysofthemonth = list(filter(lambda x: x[0] != 0, utility_calendar.itermonthdays2(year, month))) #Contains a list of tuples of the form (day, weekday)
    datesofthemonth = list(filter(lambda x: x.month==month, utility_calendar.itermonthdates(year, month))) #Contains a list of dates for each day in the month
    
    if (recurrence == 'teenth'): #If looking for the 'teenth' of a day, filter the days of the month to match just the teenth that fits, then return it
        matched_day = list(filter(lambda x: (x[0] >= 13) and (x[0] <=19) and (x[1] == days_dictionary[day]), daysofthemonth))
        return date(year, month, matched_day[0][0]) #Since match_day will be a list containing a single tuple, we must access the actual date using [0][0]
    else: #If looking for a particular occurence of a day, filter the days, then look for the reoccurence to find the index of the matching date
        matched_days = list(filter(lambda x: x[1] == days_dictionary[day], daysofthemonth))
        if recurrence == 'last':
            return datesofthemonth[matched_days.pop()[0]-1]
        else:
            return datesofthemonth[matched_days[recurrence_dictionary[recurrence]][0]-1] 
