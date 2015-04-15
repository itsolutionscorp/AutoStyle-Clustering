#!/usr/bin/env python
# -*- coding: utf-8 -*-
from datetime import date, timedelta

def add_gigasecond(birthdate):
    return birthdate + timedelta(seconds=10**9)
