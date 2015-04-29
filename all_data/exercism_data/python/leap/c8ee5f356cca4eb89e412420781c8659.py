#!/usr/bin/python
# -*- coding: utf-8 -*-


def _is_dividable_by(year, nr):
    if(not (year % nr)):
        return True
    else:
        return False


def is_leap_year(year):
    if((_is_dividable_by(year, 4) and not _is_dividable_by(year, 100)) or
       (_is_dividable_by(year, 100) and _is_dividable_by(year, 400))
       ):
        return True
    else:
        return False
