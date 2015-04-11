#!/usr/bin/env python

from string import ascii_lowercase as abc

cba = abc[::-1] # reverse string

def encode(text):
    return atbash(text, "enc")
    
def decode(text):
    return atbash(text, "dec")
    
def atbash(text, mode):
    text = text.lower()
    output = ""
    for c in text:
        if c in abc:
            index = abc.index(c)
            output += cba[index]
        elif c.isdigit():
            output += c
        else:
            pass
    return format_output(output, mode)

def format_output(txt, mode):
    if mode == "enc":
        return ' '.join(txt[i:i+5] for i in range(0, len(txt), 5))
    else:
        return ''.join(c for c in txt)
