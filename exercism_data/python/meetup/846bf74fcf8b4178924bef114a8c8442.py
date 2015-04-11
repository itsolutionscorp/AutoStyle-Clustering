import datetime
from calendar import monthrange

def meetup_day(year, month, dow, ident):
    if ident == 'teenth':
        for item in range(11,19):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)

    if ident == '1st':
        for item in range (1,8):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)

    if ident == '2nd':
        for item in range (8,15):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)

    if ident == '3rd':
        for item in range (15,22):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)

    if ident == '4th':
        for item in range (22,31):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)

    if ident == 'last':

        for item in reversed(range(23,monthrange(year,month)[1]+1)):
            date = datetime.date(year, month, item)
            if date.strftime('%A') == dow:
                return datetime.date(year, month, item)
        

# FAIL: test_last_thursday_of_october_2013 (__main__.MeetupTest)
# ----------------------------------------------------------------------
# Traceback (most recent call last):
#   File "meetup_test.py", line 34, in test_last_thursday_of_october_2013
#     meetup_day(2013, 10, 'Thursday', 'last'))
# AssertionError: datetime.date(2013, 10, 31) != datetime.date(2013, 10, 24)

# ----------------------------------------------------------------------
