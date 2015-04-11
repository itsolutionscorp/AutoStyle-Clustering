# -*- coding: utf-8 -*-
"""
Created on Thu Sep 25 16:45:09 2014

@author: laegrim
"""

def sieve(limit):

    starting_list = range(2, limit + 1)
    prime_list = []

    while starting_list != []:

        prime_list.append(starting_list[0])
        for num in starting_list:
            if num % prime_list[-1] == 0:
                starting_list.pop(starting_list.index(num))

    return prime_list
