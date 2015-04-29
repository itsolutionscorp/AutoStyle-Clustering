#!/usr/bin/env python
# -*- coding: UTF-8 -*-

"""
Sum of multiples
"""

__version__ = '0.0.1'
__all__ = ['__version__', 'SumOfMultiples']

class SumOfMultiples(object):
    def __init__(self, *args):
        self.multiples = args or (3, 5)

    def to(self, num):
        return sum([
            n for n in xrange(num) if
            any([not n % m for m in self.multiples])
        ])
