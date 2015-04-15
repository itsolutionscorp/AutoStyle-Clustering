# -*- coding: utf-8 -*-

def accumulate(coll, fn):
    return [fn(x) for x in coll]
