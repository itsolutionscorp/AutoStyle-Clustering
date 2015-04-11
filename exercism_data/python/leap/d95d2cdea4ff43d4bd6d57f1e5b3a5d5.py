def year_divs_by_400(year):
    return year % 400 == 0
def not_divs_by_100(year):
    return not(year % 100) == 0
def year_divs_by_4(year):
    return year % 4 == 0

def is_leap_year(year):
    #deliver whether year is leap year
    return (year_divs_by_400(year) or
           year_divs_by_4(year) and not_divs_by_100(year)
           )
