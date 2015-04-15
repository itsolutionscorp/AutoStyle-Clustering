def is_leap_year(yr):
    return (not( (yr % 100 == 0 and yr % 400 != 0) or yr % 4 !=0 ) )
