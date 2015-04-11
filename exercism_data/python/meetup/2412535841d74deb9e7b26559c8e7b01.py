import calendar
from datetime import date

def meetup_day(year, month, day, seq):

    my = calendar.monthcalendar(year, month)

    day_index = {
        'Monday': 0
        'Tuesday': 1
        'Wednesday': 2
        'Thursday': 3
        'Friday': 4
        'Saturday': 5
        'Sunday': 6
    }

    '''
    if day == 'Monday':
        day_index = 0
    if day == 'Tuesday':
        day_index = 1
    if day == 'Wednesday':
        day_index = 2
    if day == 'Thursday':
        day_index = 3
    if day == 'Friday':
        day_index = 4
    if day == 'Saturday':
        day_index = 5
    if day == 'Sunday':
        day_index = 6
    '''



    def find_first(year, month, day):
        week_counter = 0
        d = my[week_counter][day_index]
        if d > 0:
            result = my[week_counter][day_index]
        else:
            result = my[week_counter + 1][day_index]
        return result

    def find_second(year, month, day):
        week_counter = 0
        d = my[week_counter][day_index]
        if d > 0:
            result = my[week_counter + 1][day_index]
        else:
            result = my[week_counter + 2][day_index]
        return result

    def find_third(year, month, day):
        week_counter = 0
        d = my[week_counter][day_index]
        if d > 0:
            result = my[week_counter + 2][day_index]
        else:
            result = my[week_counter + 3][day_index]
        return result

    def find_fourth(year, month, day):
        week_counter = 0
        d = my[week_counter][day_index]
        if d > 0:
            result = my[week_counter + 3][day_index]
        else:
            result = my[week_counter + 4][day_index]
        return result

    def find_last(year, month, day):
        week_counter = -1
        d = my[week_counter][day_index]
        if d > 0:
            result = my[week_counter][day_index]
        else:
            result = my[week_counter - 1][day_index]
        return result

    def find_teenth(year, month, day):
        for index, item in enumerate(my):
            if 13 in my[index]:
                week_counter = index
                if my[week_counter][day_index] > 12:
                    result = my[week_counter][day_index]
                else:
                    result = my[week_counter + 1][day_index]
                return result

#initialize functions for proper sequence

    if seq == '1st':
        result = find_first(year, month, day)
    if seq == '2nd':
        result = find_second(year, month, day)
    if seq == '3rd':
        result = find_third(year, month, day)
    if seq == '4th':
        result = find_fourth(year, month, day)
    if seq == 'last':
        result = find_last(year, month, day)
    if seq == 'teenth':
        result = find_teenth(year, month, day)

    return date(year, month, result)
