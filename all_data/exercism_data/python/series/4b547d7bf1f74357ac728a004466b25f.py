#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
Series
"""

__version__ = "0.0.2"
__all__ = ['__version__', 'slices']

def slices(digits, num):
    """Take a string of `digits` and return all possible consecutive
    number series of length `num` in that string.

    .. versionadded:: 0.0.1
    .. versionchanged:: 0.0.2

    :param digits: the string of digits
    :param num: the slice length of the digits
    """
    if 0 < num <= len(digits):
        return [[int(d) for d in digits[i:i+num]] for i in xrange(len(digits)-num+1)]

    raise ValueError("bad slice length")
