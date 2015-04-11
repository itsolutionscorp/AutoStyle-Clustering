# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 10:33:52 2014
"""


def hey(input_text):

    count_of_upper = 0
    count_of_alpha = 0

    for c in input_text:
        if c.isupper():
            count_of_upper += 1
        if c.isalpha():
            count_of_alpha += 1

    if len(input_text.strip()) < 1:
        response_text = 'Fine. Be that way!'

    elif count_of_upper == count_of_alpha and count_of_upper != 0:
        response_text = 'Whoa, chill out!'

    elif input_text[-1] == '?':
        response_text = 'Sure.'

    else:
        response_text = 'Whatever.'

    return response_text
