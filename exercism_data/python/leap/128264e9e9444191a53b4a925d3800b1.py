# -*- coding: utf-8 -*-

def is_leap_year(year):

    '''
    on every year that is evenly divisible by 4
    except every year that is evenly divisible by 100
    unless the year is also evenly divisible by 400

    so

    if year is divisible by 400 is leap return True
    if previous is False and year is divisible by 100 is not leap return False
    if previous is True and year is divisible by 4 is leap return True
    everything else return False
    '''

    if year % 400 == 0:
        return True

    if year % 100 == 0:
        return False

    if year % 4 == 0:
        return True

    return False
