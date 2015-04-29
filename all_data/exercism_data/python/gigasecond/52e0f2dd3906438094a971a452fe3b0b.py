# -*- coding: utf-8 -*-
#
#calculate the date that a person's gigasecond birthday is.
#
#datetime.timedelta(seconds=t))

from datetime import date
from datetime import timedelta

def add_gigasecond(birthdate):
    
    OneGigaSecond = 1000000000
    
    date_gap = timedelta(seconds=OneGigaSecond)
    
    OneGs_bday = birthdate + date_gap
    
    return OneGs_bday
    
