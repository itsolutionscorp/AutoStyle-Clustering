from calendar import monthrange
from datetime import date, timedelta


def meetup_day(yr, mth, weekday, freq):

    days_of_week_dct = {'Monday': 0,
                        'Tuesday': 1,
                        'Wednesday': 2,
                        'Thursday': 3,
                        'Friday': 4,
                        'Saturday': 5,
                        'Sunday': 6}

    freq_dct = {'1st': 0,
                '2nd': 1,
                '3rd': 2,
                '4th': 3,
                'last': 4}

    teenth_days = range(13, 20)  # NB as the top end of the range 20 is not
                                 # included, so we just have the teenth days

    weekday_index = days_of_week_dct[weekday]
    start_of_month, days_in_month = monthrange(yr, mth)
    first_week_date = (weekday_index - start_of_month) % 7 + 1

    if freq != 'teenth':
        freq_index = freq_dct[freq]
        return date(yr, mth, first_week_date) + timedelta(weeks=freq_index)
    else:
        for days in teenth_days:
            if date(yr, mth, days).weekday() == weekday_index:
                return date(yr, mth, days)
