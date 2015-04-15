from datetime import date
import calendar

dow = dict(zip(range(0, 7), "Monday Tuesday Wednesday Thursday Friday Saturday Sunday".split()))
lies_on_dict = dict(zip("1st 2nd 3rd 4th".split(), range(0, 4)))

def meetup_day(y, m, day, lies_on):
        d = date(y, m, 1)
        if lies_on == "teenth":
            for x in range(11, 20):
                d_temp = d.replace(day=x)
                if dow[d_temp.weekday()] == day:
                    return d_temp
        matching_dates = []
        for x in range(1,calendar.monthrange(y, m)[1]+1):
            d_temp = d.replace(day=x)
            if dow[d_temp.weekday()] == day:
                matching_dates.append(d_temp)
        if lies_on == 'last':
            return matching_dates[-1]
        else:
            return matching_dates[lies_on_dict[lies_on]]
