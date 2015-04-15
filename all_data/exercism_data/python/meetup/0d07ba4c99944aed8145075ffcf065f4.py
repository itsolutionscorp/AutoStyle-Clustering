import calendar as cld
from datetime import date

def meetup_day(year, month, day, order):
    '''
    Returns the date the order-th day in a month
    of a certain years occurs
    '''
    cal = cld.Calendar()
    day_num = getDayNumber(day)
    # get day number and weekday number tuples for a year, month
    month_days = cal.monthdays2calendar(year, month)
    # stores day number and weekday number tuples for a day of week
    # only adds tuples with valid day numbers (i.e. != 0)
    day_vals = [week[day_num] for week in month_days if week[day_num][0] != 0]
    # get index that nth day will appear in day_vals
    order_index = getIndexForOrderDay(order, day_vals)
    try:
        day_num = day_vals[order_index][0]
    except IndexError:
        raise MeetupDayException("Bad Date!")

    return date(year, month, day_num)




def getDayNumber(day):
    '''
    Returns the numerical representation of
    a days represented as a string
    '''
    days = {'Monday': 0, 'Tuesday': 1, 'Wednesday': 2,
            'Thursday': 3, 'Friday': 4, 'Saturday': 5,
            'Sunday': 6}
    return days[day]


def getIndexForOrderDay(order, day_vals):
    '''
    Returns index for the order-th day in
    day_vals
    '''
    order_nums = {'1st': 0, '2nd': 1, '3rd': 2,
                  '4th': 3, '5th': 4, 'last': -1}

    if order == 'teenth':
        for idx in range(len(day_vals)):
            day_num = day_vals[idx][0]
            if 12 < day_num < 20:
                return idx
    return order_nums[order]

class MeetupDayException(Exception):
    def __int__(self, arg):
        self.msg = arg
