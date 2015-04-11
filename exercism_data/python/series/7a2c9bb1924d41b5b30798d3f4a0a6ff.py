"""Tests for the series exercise

Implementation note:
The slices function should raise a ValueError with a meaningful error
message if its length argument doesn't fit the series.
"""
import unittest

def slices(text, n):
    if len(text) < n or not n:
        raise ValueError
    return [[int(x) for x in text[k:k+n]] for k in xrange(len(text) - n + 1)]
