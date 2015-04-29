import itertools


def even(n):
    return n % 2 == 0


def clip_under_10(n):
    return n - 9 if n > 9 else n


def addends(n):
    digits_from_right = map(int, reversed(str(n)))
    double_every_other = (x if even(i) else x * 2
                          for i, x in enumerate(digits_from_right))
    return list(map(clip_under_10, double_every_other))


def cache_result(wrapped):
    """A simple wrapper, inspired by classtools reify, that calls the
    decorated method, then hides the method with a lambda you can call to get
    the result you got that one time you actually called the underlying
    method."""
    def wrapper(obj):
        rv = wrapped(obj)

        def replacement(*args, **kwargs):
            return rv

        obj.__dict__[wrapped.__name__] = replacement
        return rv

    return wrapper


class Luhn:
    """calculates a number of properties related to the Luhn formula"""
    def __init__(self, n):
        self._n = n

    @classmethod
    def create(cls, n):
        shift = n * 10
        for x in itertools.count(shift):
            if Luhn(x).is_valid():
                return x

    @cache_result
    def addends(self):
        return addends(self._n)

    @cache_result
    def checksum(self):
        return sum(self.addends()) % 10

    @cache_result
    def is_valid(self):
        return self.checksum() == 0
