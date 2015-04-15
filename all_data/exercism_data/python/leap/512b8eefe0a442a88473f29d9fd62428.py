def is_leap_year(year):
    try:
        year = int(year)
    except:
        return "You must input a integer"
    if year%4==0 and year%100!=0 or year%400==0:
        return True
    else:
        return False
