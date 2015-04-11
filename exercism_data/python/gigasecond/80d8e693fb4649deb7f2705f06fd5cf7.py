#!/usr/bin/python3 -V
# -*- coding: utf-8 -*-

import time
from datetime import datetime
from datetime import timedelta

def add_gigasecond(origin):
	return origin + timedelta(seconds=10**9)
