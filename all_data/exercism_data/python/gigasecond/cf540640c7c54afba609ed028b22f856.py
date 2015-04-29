# -*- coding: utf-8 -*-
"""
Created on Tue Oct 28 22:43:53 2014

@author: DiCar
"""
import datetime

def add_gigasecond(birthday):
    GsAnniversary = birthday + datetime.timedelta(seconds = 10**9)
    return GsAnniversary
