from __future__ import division

class RomanNumeral(object):

    digits = [(1000, 'M'),
              ( 900, 'CM'),
              ( 500, 'D'),
              ( 400, 'CD'),
              ( 100, 'C'),
              (  90, 'XC'),
              (  50, 'L'),
              (  40, 'XL'),
              (  10, 'X'),
              (   9, 'IX'),
              (   5, 'V'),
              (   4, 'IV'),
              (   1, 'I')]

    def __init__(self, n):
        self.n = n
        ds = []
        for v, s in self.digits:
            ds.append(s*(n//v))
            n = n % v
        self.s = "".join(ds)

    def __str__(self):
        return self.s

    def __repr__(self):
        return '`{}`'.format(self.s)
