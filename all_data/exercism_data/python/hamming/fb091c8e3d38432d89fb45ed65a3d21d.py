#!/usr/bin/env python
# -*- coding: utf-8 -*-


def distance(one, two):
    return len([i for i in range(0, len(one)) if one[i] != two[i]])
