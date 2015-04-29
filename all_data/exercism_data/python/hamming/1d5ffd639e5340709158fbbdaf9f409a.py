# -*- coding: utf-8 -*-
from itertools import izip_longest

def hamming(strand_a, strand_b):
    return sum([a!=b for (a,b) in izip_longest(strand_a, strand_b)])
