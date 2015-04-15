#!/usr/bin/python
# encoding: utf-8

'''
# Gigasecond

Write a program that will calculate the date that someone turned or will celebrate their 1 Gs anniversary.

A gigasecond is one billion (10**9) seconds.


## Source

Chapter 9 in Chris Pine's online Learn to Program tutorial. [view source](http://pine.fm/LearnToProgram/?Chapter=09)
'''

from datetime import timedelta


#
def add_gigasecond(date):
    try:
        date = date + timedelta(seconds=10**9)
    except:
        return None
    return date

# vi:ts=4:sw=4:expandtab:ft=python:
