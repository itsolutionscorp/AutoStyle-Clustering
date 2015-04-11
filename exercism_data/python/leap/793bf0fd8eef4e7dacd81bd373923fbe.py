def YearDivsBy400(year):
    return year % 400 == 0
def NotDivsBy100(year):
    return not(year % 100) == 0
def YearDivsBy4(year):
    return year % 4 == 0

def is_leap_year(year):
    #deliver whether year is leap year
    return(YearDivsBy400(year) or
           (YearDivsBy4(year) and NotDivsBy100(year))
           )
