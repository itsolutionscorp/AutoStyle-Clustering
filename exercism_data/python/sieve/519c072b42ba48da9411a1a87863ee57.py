# -*- coding: utf-8 -*-
import math

def sieve(given_number):
    ans = [2]
    search_list = [x for x in range(3, given_number + 1) if x % 2 != 0]

    while(math.sqrt(given_number) > search_list[0]):
        ans.append(search_list[0])
        search_list = [x for x in search_list if x % search_list[0] != 0]
    ans += search_list
    return ans
