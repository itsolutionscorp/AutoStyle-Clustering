#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

from datetime import timedelta

def add_gigasecond(origin):
	return origin + timedelta(seconds=10**9)
