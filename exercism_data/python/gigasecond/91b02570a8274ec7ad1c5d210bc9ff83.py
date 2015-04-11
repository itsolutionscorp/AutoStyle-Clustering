#!/usr/bin/python


from datetime import timedelta


def add_gigasecond(dt):
    """ Simply using the proper datetime class....:D"""
    return dt + timedelta(seconds=10**9)
