#!/usr/bin/python

## Original and new are kind of arbitrary as it works both ways
def hamming(original, new):
    return (sum(1 for a, b in zip(original, new) if a != b) +
            abs(len(original) - len(new)))
