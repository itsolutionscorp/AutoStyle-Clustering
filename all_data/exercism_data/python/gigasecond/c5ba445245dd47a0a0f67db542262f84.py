#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import datetime

def add_gigasecond(dt):
    return dt + datetime.timedelta(seconds=10**9)

if __name__ == '__main__':
    print 'This script is not meant to be used this way.'
