from datetime import date

def meetup_day(year, month, weekday, attribute):
    
    def month_length(y,m):
        d=28
        try:
            while date(y, m, d+1):
                d += 1
        except:
            return d

    def make_weekday_list(y, m, wd):
        for i in range(1,8):
            if date(y,m,i).strftime('%A') == wd:
                return list(range(i, month_length(y,m) + 1, 7))

    def get_day(same_wd, att):
        if att == '1st':
            return same_wd[0]
        elif att == 'last':
            return same_wd[len(same_wd)-1]
        elif att == '2nd':
            return [x for x in same_wd if x in range(8, 10)][0]
        elif att == 'teenth':
            return [x for x in same_wd if x in range(10,17)][0]
        elif att == '3rd':
            return [x for x in same_wd if x in range(17,22)][0]
        elif att == '4th':
            return [x for x in same_wd if x in range(22,25)][0]

    same_weekdays = make_weekday_list(year, month, weekday)
    day = get_day(same_weekdays, attribute)
    
    return date(year, month, day)
