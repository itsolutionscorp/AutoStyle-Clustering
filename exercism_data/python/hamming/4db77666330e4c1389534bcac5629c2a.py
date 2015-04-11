#!/usr/bin/python

## Original and new are kind of arbitrary as it works both ways
def hamming(original, new):
    return (sum(a != b for a, b in zip(original, new)) +
            abs(len(original) - len(new)))
