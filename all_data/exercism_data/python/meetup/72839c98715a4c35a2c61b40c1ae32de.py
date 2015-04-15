import calendar

def meetup_day(year, month, week, on):

    cal_setup = calendar.Calendar(firstweekday=0).itermonthdates(year, month)
    ret, col = None, []

    for m in cal_setup:
        if m.month == month:
            if m.strftime('%A') == week:
                col.append(m)

    if on[0].isnumeric():
        ret = col[(int(on[0])-1)]
    elif on == 'last':
        ret = col[-1]
    else:
        for i in col:
            if i.day in range(13, 20):
                ret = i
    return ret
