#!/usr/bin/python
# exercism python 13: atbash cipher

import string
import re

def encode(text):    
    chunked_text = ' '.join([x for x in chunk_gen(text)])
    encoded_text = translator(chunked_text)
    return encoded_text

def decode(text):
    return translator(remove_spaces(text))
    
def remove_spaces(text):
    return ''.join(re.findall('\w', text)).lower()
    
def chunk_gen(text, length=5):
    text = remove_spaces(text)
    for index in xrange(0, len(text), length):
        yield text[index:index+length]
        
def translator(text):
    trans_table = string.maketrans('abcdefghijklmnopqrstuvwxyz', 'zyxwvutsrqponmlkjihgfedcba')
    return string.translate(text, trans_table)
        
