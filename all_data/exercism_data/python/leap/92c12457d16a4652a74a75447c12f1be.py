#
#Created on Jan 23, 2015
#
#

def is_leap_year(year):
    if divisibleBy400(year):
        return True
    if divisibleBy4(year) and not divisibleBy100(year):
        return True
    return False
            
def divisibleBy4(year):
    return year % 4 == 0

def divisibleBy100(year):
    return year % 100 == 0

def divisibleBy400(year):
    return year % 400 == 0
