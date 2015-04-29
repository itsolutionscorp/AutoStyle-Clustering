from datetime import date, timedelta

WEEKDAYS = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2, 'Thursday': 3, 'Friday': 4, 'Saturday': 5, 'Sunday': 6}
ORDINALS = ['1st', '2nd', '3rd', '4th', '5th']
TEENS = [13, 14, 15, 16, 17, 18, 19]

def meetup_day(year, month, weekday, weekday_of_month):
    month = date(year, month, 1)
    first_requested_weekday_date = get_first_requested_weekday(month, weekday)

    try:
        ordinal_modifier = ORDINALS.index(weekday_of_month)
    except ValueError:
        ordinal_modifier = -1

    if(ordinal_modifier != -1):
        try:
            requested_weekday_date = first_requested_weekday_date.replace(day=first_requested_weekday_date.day + 7*ordinal_modifier)
        except ValueError:
            raise MeetupDayException("Boo!")
        return requested_weekday_date

    weekdays = make_weekdays(first_requested_weekday_date)
    if weekday_of_month == 'teenth':
        for weekday in weekdays:
            if weekday.teenth:
                return weekday.date

    if weekday_of_month == 'last':
        for weekday in weekdays:
            if weekday.last:
                return weekday.date

    raise MeetupDayException("Exception!")


def get_first_requested_weekday(month, requested_weekday):
	"""Return the date of the first requested weekday of the specified month."""
	requested_weekday_number = WEEKDAYS[requested_weekday]
	first_weekday = month.weekday()
	first_requested_weekday_diff = (requested_weekday_number - first_weekday) % 7

	return month + timedelta(days=first_requested_weekday_diff)

def make_weekdays(first_requested_weekday_date):
    weekdays = []
    weekday_date = first_requested_weekday_date
    for ordinal in ORDINALS:
        weekday = Weekday(weekday_date, ordinal, weekday_date.day in TEENS)
        weekdays.append(weekday)
        try:
            weekday_date = weekday_date.replace(day = weekday_date.day + 7)
        except ValueError:
            break
    return weekdays

class Weekday:
    def __init__(self, date, ordinal, teenth):
        self.date = date
        self.ordinal = ordinal
        try:
            self.ordinal_number = ORDINALS.index(ordinal)
        except ValueError:
            self.ordinal_number = -1
        self.teenth = teenth
        self.last = self.is_last(ordinal)

    def is_last(self, ordinal):
        if ordinal == '5th':
            return True
        elif ordinal == '4th':
            try:
                date(self.date.year, self.date.month, self.date.day + 7)
                return False
            except ValueError:
                return True
        else:
            return False

    def __repr__(self):
        items = ("%s = %r" % (k, v) for k, v in self.__dict__.items())
        return "<%s: {%s}>" % (self.__class__.__name__, ', '.join(items))

class MeetupDayException(Exception):
    def __init__(self,args=None):
        self.args=args
