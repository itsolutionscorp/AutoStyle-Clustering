#!/usr/bin/env python3

def is_multiple(item, multiples):
    for i in multiples:
        if not i:
            continue
        if not item % i:
            return True
    return False

def sum_of_multiples(number, multiples=[3, 5]):
    return sum([x for x in range(1, number) if is_multiple(x, multiples)])
