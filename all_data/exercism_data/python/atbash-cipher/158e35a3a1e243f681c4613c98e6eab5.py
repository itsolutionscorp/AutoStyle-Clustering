# -*- coding: utf-8 -*-
"""
Created on Mon Sep 29 17:52:46 2014

"""

def encode(input_text):
    return_string = []
    character_counter = 0
    for c in input_text.lower():
        if character_counter == 5:
            return_string.append(' ')
            character_counter = 0

        if c.isdigit():
            return_string.append(c)
            character_counter += 1
        elif c.isalpha():
            return_string.append(chr(110+(ord(c)-109)*-1))
            character_counter += 1


    return ''.join(return_string).strip()

def decode(input_text):
    return encode(input_text).replace(' ','')


