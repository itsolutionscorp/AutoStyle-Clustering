import datetime


def meetup_day(meet_year, meet_month, meet_day, loc):

    weekdays = {
        0: 'Monday',
        1: 'Tuesday',
        2: 'Wednesday',
        3: 'Thursday',
        4: 'Friday',
        5: 'Saturday',
        6: 'Sunday'
    }

    def check_date(start, end):
        date = datetime.date(meet_year, meet_month, start)
        for i in range(start, end):
            if weekdays[date.weekday()] == meet_day:
                break
            else:
                date = datetime.date(meet_year, meet_month, i)
        return date

    if loc == '1st':
        return check_date(1, 8)

    elif loc == '2nd':
        return check_date(8, 15)

    elif loc == '3rd':
        return check_date(15, 22)

    elif loc == '4th' or loc == 'last' and meet_month == 'February':
        return check_date(22, 29)

    elif loc == 'last':
        return check_date(29, 32)

    elif loc == 'teenth':
        return check_date(13, 20)

    else:
        print "Invalid inputs. Program exiting."
        return False
