# Meetup
from datetime import date
from calendar import Calendar

def meetup_day(year, month, day, choice):
    ordinal_dict = {'1st': 1, '2nd': 2, '3rd': 3, '4th': 4}
    weekday_dict = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
                    'Friday': 4, 'Saturday': 5, 'Sunday': 6}
    
    calendar = Calendar()
    possibilities = list(calendar.itermonthdays2(year, month))
    
    if choice in ordinal_dict:
        current_ordinal = 0
        for possible_date in possibilities:
            if possible_date[0] != 0 and possible_date[1] == weekday_dict[day]:
                current_ordinal += 1
                if current_ordinal == ordinal_dict[choice]:
                    return date(year, month, possible_date[0])               
    
    elif choice == 'last':
        for possible_date in possibilities:
            if possible_date[0] != 0 and possible_date[1] == weekday_dict[day]:
                target_date = possible_date[0]
        return date(year, month, target_date)
                
    elif choice == 'teenth':
        for possible_date in possibilities:
            if possible_date[0] in range(13, 20) and \
                possible_date[1] == weekday_dict[day]:
                return date(year, month, possible_date[0])
