#!/usr/bin/env python2
# -*- coding: utf-8 -*-

#import datetime, calendar

from datetime import date as dt
from calendar import monthcalendar as mc
from calendar import weekheader as wh

def meetup_day(yr, mo, dow, modal):

    # Ordinal list of weekdays according to datetime.datetime.weekday()
    #wds = ['Mon','Tue','Wed','Thu','Fri','Sat','Sun']
    wds = wh(3).split()
    wdow = wds.index(dow[0:3])

    mocal = mc(int(yr), int(mo))
    dom = [x[wdow] for x in mocal if x[wdow] != 0]

    if modal.lower() == 'last' or modal == '5th':
        return dt(int(yr), int(mo), dom[-1])
    elif modal.lower() == 'first' or modal == '1st':
        return dt(int(yr), int(mo), dom[0])
    elif modal.lower() == 'second' or modal == '2nd':
        return dt(int(yr), int(mo), dom[1])
    elif modal.lower() == 'third' or modal == '3rd':
        return dt(int(yr), int(mo), dom[2])
    elif modal.lower() == 'forth' or modal == '4th':
        return dt(int(yr), int(mo), dom[3])
    elif modal == 'teenth':
        return dt(int(yr), int(mo), [x for x in dom if x >= 13 and x <=19][0])
    else:
        Exception("Undetected date specification.")

if __name__ == '__main__':
    print '%s' % ('This script is not meant to be used this way.')
