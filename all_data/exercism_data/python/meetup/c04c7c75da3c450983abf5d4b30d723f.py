import datetime

WEEKDAYS = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday']

def meetup_day(year, month, week_day, choice):
    first = datetime.date(year, month, 1).weekday()
    first_of_day = (WEEKDAYS.index(week_day) - first) % 7 + 1

    if choice == '1st':
        day = first_of_day
    elif choice == '2nd':
        day = first_of_day + 7
    elif choice == '3rd':
        day = first_of_day + 14
    elif choice == '4th':
        day = first_of_day + 21
    elif choice == 'last':
        try:
            day = first_of_day + 28
            datetime.date(year, month, day)
        except ValueError:
            day = first_of_day + 21
    elif choice == 'teenth':
        if first_of_day < 6:
            day = first_of_day + 14
        else:
            day = first_of_day + 7
    else:
        raise "Unrecognised choice of week"
    return datetime.date(year, month, day)
