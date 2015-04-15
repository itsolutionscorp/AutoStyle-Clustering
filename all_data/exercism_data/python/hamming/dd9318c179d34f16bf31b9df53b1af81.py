#!/usr/bin/env python


def distance(first, second):
    return sum(1 if first[index] != second[index] else 0
               for index in range(min(len(first), len(second))))


# vim:syn=python:et:
