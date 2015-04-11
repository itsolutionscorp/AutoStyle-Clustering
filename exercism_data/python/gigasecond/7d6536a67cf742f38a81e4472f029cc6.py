#!/usr/bin/env python
# encoding: utf-8

from datetime import date

def add_gigasecond(date):
    gs_anniv = int(date.strftime("%s")) + 10**9
    return date.fromtimestamp(gs_anniv)
