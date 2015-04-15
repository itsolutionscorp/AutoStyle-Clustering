from datetime import date

def leap_year(year):
    return  year % 4 == 0 and year % 100 != 0 or year % 400 == 0 


def meetup_day(year, month, day, position):
    months = { 1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30,
              7: 31, 8: 31, 9: 30, 10: 31, 11: 30, 12:31}

    pos = { '1st': 1, '2nd': 2, '3rd': 3, '4th': 4,
            'last': -1, 'teenth': [13, 14, 15, 16, 17, 18, 19]
    }

    int_day = { 'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3,
                'Friday': 4, 'Saturday': 5, 'Sunday' : 6}

    if leap_year(year):
        months[2] = 29

    num_day = int_day[day]
    pos_number = pos[position]
    num_range = months[month]

    if isinstance(pos_number, list) :
        for i in pos_number:
            if date(year, month, i).weekday() == num_day:
                return date(year, month, i)
    else:
        control = 0
        if pos_number >= 0:
            for i in range(1, num_range + 1):
                if date(year, month, i).weekday() == num_day:
                    control += 1
                    if control == pos_number:
                        return date(year, month, i)
        else:
            for i in range(num_range, 1, -1):
                if date(year, month, i).weekday() == num_day:
                    return date(year, month, i)



    return
