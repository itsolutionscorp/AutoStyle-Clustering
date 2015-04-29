#!/usr/bin/env python3
# -*- coding: utf-8 -*-

# Submission file for the python sieve exercise.
#
# v1:


def sieve(count):

    numbers = [True]*count
    numbers[0] = False
    numbers[1] = False

    i = 2

    # run until sqrt(count)
    while i*i <= count:
        for k in range(2*i, count, i):
            numbers[k] = False
        i += 1

    return [x[0] for x in enumerate(numbers) if x[1]]
