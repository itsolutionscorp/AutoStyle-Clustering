# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 17:02:07 2014

"""


def sieve(input_num):
    prime_check_range = []
    for i in range(input_num):
        prime_check_range.append([i+1, None])

    for i in range(1, input_num):
        if prime_check_range[i][1] is None:
            prime_check_range[i][1] = True
            for j in range(i+1, input_num):
                if prime_check_range[j][0] % prime_check_range[i][0] == 0:
                    prime_check_range[j][1] = False

    return_list = []
    for num, prime in prime_check_range:
        if prime:
            return_list.append(num)

    return return_list

