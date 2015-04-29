#!/usr/bin/env python3
def transform(old):
    expected = {}
    for k, v in old.items():
        for i in v:
            expected[i.lower()] = k
    return expected
