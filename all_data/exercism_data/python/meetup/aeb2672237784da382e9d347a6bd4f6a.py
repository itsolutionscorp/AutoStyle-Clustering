from datetime import date
import calendar

day_dict = {"Monday":0,"Tuesday":1,"Wednesday":2,
            "Thursday":3,"Friday":4,"Saturday":5,"Sunday":6}

def meetup_day(year, month, day, occurance):
    
    key_month_values = calendar.monthrange(year,month) # (day of the first, range of month)
    first_day_int = key_month_values[0]
    target_day_int = day_dict[day]

    #calculate date of first occurance
    first_occurance = (target_day_int - first_day_int + 8) % 7 
    if first_occurance == 0:
        first_occurance = 7

    # get the date from occurance
    if occurance == "1st":
        meetup_date = first_occurance
    elif occurance == "2nd":
        meetup_date = first_occurance + 7
    elif occurance == "3rd":
        meetup_date = first_occurance + 14
    elif occurance == "4th":
        meetup_date = first_occurance + 21
    elif occurance == "5th":
        meetup_date = first_occurance + 28
    elif occurance == "teenth":
        meetup_date = first_occurance + 14 if first_occurance + 14 in range(13,20) else first_occurance + 7
    elif occurance == "last":
        last_day_of_month = key_month_values[1]
        meetup_date = first_occurance + 28 if first_occurance + 28 <= last_day_of_month else first_occurance + 21

    return date(year,month,meetup_date)
