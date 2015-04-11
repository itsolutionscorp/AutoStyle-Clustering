# -*- coding: utf-8 -*-
"""
Created on Mon Oct  6 21:38:14 2014

@author: luke
"""

import datetime

def add_gigasecond(birthDate):
    
    gigaSec=1000000000
    gigaDay=gigaSec/60/60/24
    gigaDay = datetime.timedelta(gigaDay)
    
    gigaDate = birthDate + gigaDay    
    
    return gigaDate
