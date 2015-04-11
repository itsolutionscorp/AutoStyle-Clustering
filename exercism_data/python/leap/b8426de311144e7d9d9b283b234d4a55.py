def is_leap_year(yr):
    return ((not( yr % 100== 0) or not( yr % 400 != 0)) and not(yr % 4 !=0 ) )
