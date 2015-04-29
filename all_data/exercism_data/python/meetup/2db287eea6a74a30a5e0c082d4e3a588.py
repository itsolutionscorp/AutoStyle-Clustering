from datetime import date, timedelta


def meetup_day(year, month, weekday, filterday):
    test_date = date(year, month, 1)
    # To find out how many days in the month.
    # Add one month, subtract the original date and find the days between
    if month == 12:
        # December breaks the logic and it always returns 31
        total_days = 31
    else:
        total_days = (date(year, month + 1, 1) - test_date).days
    while test_date.month == month:
        if test_date.strftime('%A') == weekday:
            if filterday == '1st' and test_date.day <= 7:
                return test_date
            if filterday == '2nd' and test_date.day > 7:
                return test_date
            if filterday == 'teenth' and test_date.day >= 13:
                return test_date
            if filterday == '3rd' and test_date.day > 14:
                return test_date
            if filterday == '4th' and test_date.day > 21:
                return test_date
            if filterday == 'last' and test_date.day > total_days - 7:
                return test_date
        test_date += timedelta(days=1)
