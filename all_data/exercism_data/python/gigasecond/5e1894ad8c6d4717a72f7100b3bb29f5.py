#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import datetime

def add_gigasecond(dt):
    return dt + datetime.timedelta(seconds=1000000000)

if __name__ == '__main__':
    print '%s' % ('This script is meant to be used this way.')
