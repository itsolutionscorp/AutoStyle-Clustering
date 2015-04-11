#!/usr/bin/env python3


def slices(string, length) -> list:

    if length == 0 or length > len(string):
        raise ValueError

    len_string = len(string)
    max_index = len_string - length + 1

    string_list = [list(string[i:i+length]) for i in range(max_index)]
    int_list = [list(map(int, i)) for i in string_list]

    return int_list
