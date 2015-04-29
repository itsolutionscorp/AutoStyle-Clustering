#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import string

def word_count(wds):
    np = wds.lower().translate(None, string.punctuation)
    return { y : len([z for z in np.split() if z == y]) for y in set([x for x in np.split()]) }

if __name__ == '__main__':
    print '%s' % ('This script is meant to be used this way.')
