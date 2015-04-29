from datetime import date
import calendar

def meetup_day(year, month, day_name, spec):
  return _meetup_day(spec, day_name).in_month(month).of_year(year)

class NoSuchMeetupDayException(Exception):
    pass

class _meetup_day:

    _filters = [
        ( [ '1st', 'first'  ], lambda c: c[0] ),
        ( [ '2nd', 'second' ], lambda c: c[1] ),
        ( [ '3rd', 'third'  ], lambda c: c[2] ),
        ( [ '4th', 'fourth' ], lambda c: c[3] ),
        ( [ '5th', 'fifth'  ], lambda c: c[4] ),
        ( [ 'last'          ], lambda c: c[-1] ),
        ( [ 'teenth'        ], lambda c: [ d for d in c if d.day >= 13 ][0] )
    ]

    def __init__(self, spec, day_name):
        self.spec_filter = self._get_spec_filter(spec)
        self.day_number = self._get_day_number(day_name)

    def _get_spec_filter(self, spec):
        try:
            return [
                f for specs, f in self._filters
                if spec in specs ][0]
        except IndexError:
            raise ValueError(spec + ": unknown specifier")

    def _get_day_number(self, day_name):
        try:
            return calendar.day_name[:].index(day_name)
        except ValueError:
            raise ValueError(day_name + ": unknown day name")

    def in_month(self, month):
        self.month = month
        return self

    def of_year(self, year):
        self.year = year
        return self.find()

    def find(self):
        try:
            return self.spec_filter(self._get_candidates())
        except IndexError:
            raise NoSuchMeetupDayException('No such meet day exists')

    def _get_candidates(self):
        """
        Retrieves a list of all dates within the requested year and month
        for which the day equals the requested day. So it would for example
        return a list of dates for all mondays in September 2014.
        Note: candi"dates" pun intended ;-)
        """
        weeks_in_month = calendar.monthcalendar(self.year, self.month)
        return [
            date(self.year, self.month, week[self.day_number])
            for week in weeks_in_month  
            if week[self.day_number] != 0 ]
