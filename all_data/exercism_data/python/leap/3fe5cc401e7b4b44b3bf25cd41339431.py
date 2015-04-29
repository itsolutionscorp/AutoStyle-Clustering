#
#

def YearDivsBy400(year):
    return year % 400 == 0
def YearDivsBy100(year):
    return year % 100 == 0
def YearDivsBy4(year):
    return year % 4 == 0

def is_leap_year(year):
    return (YearDivsBy400(year) or (not(YearDivsBy100(year)) and YearDivsBy4(year)))
