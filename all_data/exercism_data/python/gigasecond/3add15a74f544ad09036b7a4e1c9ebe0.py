#!/usr/bin/env python
# -*- coding: utf-8 -*-

from datetime import timedelta


def add_gigasecond(givendate):
    return givendate + timedelta(seconds=1e9)
