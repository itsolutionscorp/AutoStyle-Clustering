from datetime import date, timedelta

weekday_to_int = {'Monday': 0, 
                  'Tuesday': 1,
                  'Wednesday': 2,
                  'Thursday': 3,
                  'Friday': 4,
                  'Saturday': 5,
                  'Sunday': 6}
order_to_int = {'1st': 0,
                '2nd': 1,
                '3rd': 2,
                '4th': 3}
    
def meetup_day(year, month, weekday, order):
    weekday = weekday_to_int[weekday]
    firstday = date(year, month, 1)
    if order == 'teenth':
        thirteenthday = firstday + timedelta(12)
        delta = (weekday - thirteenthday.weekday()) % 7
        return thirteenthday + timedelta(delta)
    delta = (weekday - firstday.weekday()) % 7
    indexday = firstday + timedelta(delta)
    weekdelta = timedelta(7)
    if order == 'last':
        while indexday.month == month:
            indexday += weekdelta
        return indexday - weekdelta
    order = order_to_int[order]
    return indexday + weekdelta * order
        
