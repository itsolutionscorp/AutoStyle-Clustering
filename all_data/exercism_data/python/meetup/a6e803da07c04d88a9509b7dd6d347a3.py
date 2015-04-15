from datetime import date
from datetime import timedelta

def weekday_word_to_number( weekday ):
    return { 'Monday':0,
             'Tuesday':1,
             'Wednesday':2,
             'Thursday':3,
             'Friday':4,
             'Saturday':5,
             'Sunday':6 }[ weekday ]

# Returns all dates in a month of a given day of the week
def find_all_somedays( year, month, weekday ):
    my_day = date( year, month, 1 )
    weekday_number = weekday_word_to_number( weekday )

    day_list = []
    while my_day.month == month:
        if my_day.weekday() == weekday_number:
            day_list.append( my_day )
        my_day += timedelta( days = 1 )
    return day_list
        

def meetup_day( year, month, weekday, which ):

    day_list = find_all_somedays( year, month, weekday )

    if which == 'teenth':
        for day in day_list:
            if day.day in range( 13, 20 ):
                return day

    return { '1st':day_list[0],
             '2nd':day_list[1],
             '3rd':day_list[2],
             '4th':day_list[3],
             'last':day_list[-1] }[ which ]
