#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import string

def word_count(wds):
    """Returns a dictionary of {str('word'):int(count),...}
    """

    # new paragraph, words lowered, punctuation stripped
    np = wds.lower().translate(None, string.punctuation).split()

    # We could go "explicit" and roll funky loops like...
    # ret = {}
    # for y in set(np):
    #     tmp = [z for z in np if z == y] # gets words that match set(y)
    #     ret[y] = len(tmp)
    # Or we could use python generators (nifty stuff).

    # The line below was less readable before... adding the .split() above.
    return { y : len([z for z in np if z == y]) for y in set(np) }

if __name__ == '__main__':
    print 'This script is not meant to be used this way.'
