from datetime import date
from calendar import monthrange

def meetup_day(year, month, day_of_week, day):
    num_days_in_month = monthrange(year, month)[1]
    first_day_num = 0

    for day_num in range(1, num_days_in_month):
        test_date = date(year, month, day_num)
        test_day_of_week = test_date.strftime('%A')
        if test_day_of_week == day_of_week:
            first_day_num = day_num
            break

    #now we know the first number date of the first day_of_week, extrapolate the others
    day_num_array = [i for i in range(first_day_num, num_days_in_month + 1, 7)]

    #now match the element in day_num_array with the day param
    if day == 'first':
        final_day_num = day_num_array[0]
    elif day == 'last':
        final_day_num = day_num_array[-1]
    elif day == 'teenth':
        for day_num in day_num_array:
            if int(day_num) >= 13 and int(day_num) <= 19:
                final_day_num = day_num
                break
    else:
        day_index = int(day[0]) - 1
        final_day_num = day_num_array[day_index]

    return date(year, month, final_day_num)
