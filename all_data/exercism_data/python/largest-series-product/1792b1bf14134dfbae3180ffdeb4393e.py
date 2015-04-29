#!/usr/bin/env python3

def num_mul(a_list):
    results = 1
    for i in a_list:
        results *= i
    return results

def slices(digit_string, n):
    if n < 0 or n > len(digit_string):
        raise ValueError
    digit_string_list = [int(c) for c in list(digit_string)]
    series = [digit_string_list[a:b] for a, b in enumerate(range(n, len(digit_string)+1))]
    return series

def largest_product(digit_string, n):
    if n == 0 and len(digit_string) == '':
        return 1
    return max(map(num_mul, slices(digit_string, n)))
