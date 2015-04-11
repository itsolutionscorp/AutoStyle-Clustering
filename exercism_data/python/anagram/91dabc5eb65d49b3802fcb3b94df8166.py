#!/usr/bin/env python2
# -*- coding: utf-8 -*-

def detect_anagrams(wd, lst):
    wc = sorted(list(wd.lower()))
    ret = []
    for item in lst:
        if item.lower() == wd.lower():
            continue
        tmp = sorted(list(item.lower()))
        if len(tmp) != len(wc):
            continue
        tup = zip(wc, tmp)
        tf = True
        for x in tup:
            if x[0] != x[1]:
                tf = False
                break
        if tf:
            ret.append(item)
    return ret

if __name__ == '__main__':
    print '%s' % ('This script is not meant to be used this way.')
