#!/usr/bin/env python
# -*- coding: utf-8 -*-

from itertools import izip_longest


def distance(s1, s2):
    return sum(sym1 != sym2 for sym1, sym2 in izip_longest(s1, s2, fillvalue=" "))


def distance2(one, two):
    return len([i for i in range(0, len(one)) if one[i] != two[i]])
