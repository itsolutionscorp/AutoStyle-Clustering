"""Strain"""


def keep(iterable, pred):
    """filter(pred, iterable)"""
    return [item for item in iterable if pred(item)]


def discard(iterable, pred):
    """filter(lambda x: not pred(x), iterable)"""
    return [item for item in iterable if not pred(item)]
