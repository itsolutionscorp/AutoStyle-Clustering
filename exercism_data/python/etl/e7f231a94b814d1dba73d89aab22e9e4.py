__author__ = 'angelo'


def transform(d):
    return {item.lower(): k for k in d.iterkeys() for item in d[k]}
