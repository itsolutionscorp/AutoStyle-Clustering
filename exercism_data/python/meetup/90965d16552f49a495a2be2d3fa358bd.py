from calendar import day_name, Calendar
from datetime import date

class Meetup:

    def __init__(self, year, month):
        self.year = year
        self.month = month
        self.cal = Calendar()

    @property
    def monthly(self):
        return self.cal.monthdatescalendar(self.year, self.month)

    def weeks(self, ord_day):
        daily = (
            [day for day in week
                    if day.weekday() == ord_day and day.month == self.month]
                for week in self.monthly
        )
        return [ordinal[0] for ordinal in filter(lambda d: d != [], daily)]

def meetup_day(year, month, day_of_week, meet):

    day_n = list(day_name).index(day_of_week)
    meetup = Meetup(year, month)
    weeks  = meetup.weeks(day_n)

    if meet[0].isdigit():
        day = weeks[(int(meet[0])-1)]
    if meet == 'teenth':
        day = weeks[1].day > 10 and weeks[1] or weeks[2]
    if meet == 'last':
        day = weeks[-1]

    return day
