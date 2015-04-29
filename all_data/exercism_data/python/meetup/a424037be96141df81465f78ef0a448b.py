__author__ = 'tracyrohlin'
import datetime
from datetime import timedelta


def meetup_day(year, month, weekday, word):

    weekday_dic = {"Monday": 0, "Tuesday": 1, "Wednesday": 2, "Thursday": 3,
                    "Friday": 4, "Saturday": 5, "Sunday": 6}

    week_of_the_month = {"1st": 1, "2nd": 2, "3rd": 3, "4th": 4, "last": 5
    }

    if word == "teenth":
        for day in xrange(13, 20):
            if weekday_dic[weekday] == datetime.date(year, month, day).weekday():
                return datetime.date(year, month, day)
    else:
        """
        for date in xrange(1, amount_of_days+1):
            date_data = datetime.date(year, month, date).isocalendar()
            if date_data[2] == weekday_dic[weekday]+1:
                return year, month, week_of_the_month[word]"""

        start_first_week = datetime.date(year, month, 1)
        while start_first_week.weekday() != weekday_dic[weekday]:
            start_first_week += timedelta(days=1)
        start_first_week += timedelta(days=(week_of_the_month[word] - 1) * 7)
        return start_first_week
