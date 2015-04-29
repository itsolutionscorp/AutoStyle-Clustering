#
#
#

from datetime import date, timedelta

def meetup_day(year_num,month_num,day_str,tag_str):

    if tag_str == 'last':
        # Last day of the specified month
        d_last = date(year_num,(month_num + 1) % 12,1) - timedelta(days = 1)
        # Count back until day matches
        for i_day in range(1,8):
            if d_last.strftime('%A') == day_str:
                return d_last
            d_last = d_last - timedelta(days = 1)
    else:
        # First day of the specified month
        d_first = date(year_num,month_num,1)
        # Count forward and find first occurrence of given day
        for i_day in range(1,8):
            if d_first.strftime('%A') == day_str:
                break
            d_first = d_first + timedelta(days = 1)
        # Modify first occurrence as required
        if tag_str == '1st':
            return d_first
        if tag_str == '2nd':
            return d_first + timedelta(days = 7)
        if tag_str == '3rd':
            return d_first + timedelta(days = 14)
        if tag_str == '4th':
            return d_first + timedelta(days = 21)
        if tag_str == 'teenth':
            if d_first.day < 6:
                # Add 2 weeks
                return d_first + timedelta(days = 14)
            else:
                # Add 1 week
                return d_first + timedelta(days = 7)

