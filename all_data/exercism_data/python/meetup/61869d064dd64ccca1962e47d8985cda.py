from datetime import date

def meetup_day(year, month, wday, occurrence):
    meetup = search_start_date(year, month, occurrence)
    while meetup.weekday() != wday_word_to_int(wday):
        meetup = date(year, month, meetup.day - 1)
    return meetup

def search_start_date(year, month, occurrence):
    if occurrence == '1st':
        start_date = 7
    elif occurrence == '2nd':
        start_date = 14
    elif occurrence == 'teenth':
        start_date = 19
    elif occurrence == '3rd':
        start_date = 21
    elif occurrence == '4th':
        start_date = 28
    elif occurrence == 'last':
        start_date = 31

    # ValueError will be thrown if start_date is greater than the last
    # possible day of the month:
    while True:
        try:
            meetup = date(year, month, start_date)
        except ValueError:
            start_date -= 1
            continue
        break
    return meetup

def wday_word_to_int(wday):
    wdays = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
             'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    return wdays[wday]
