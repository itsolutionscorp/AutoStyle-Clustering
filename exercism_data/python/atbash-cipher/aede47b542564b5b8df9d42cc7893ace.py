# -*- coding: utf-8 -*-
from string import maketrans, punctuation
import re

plain_table = '0123456789abcdefghijklmnopqrstuvwxyz'
cipher_table = '0123456789zyxwvutsrqponmlkjihgfedcba'
encode_table = maketrans(plain_table, cipher_table)
decode_table = maketrans(cipher_table, plain_table)
group_len = 5

def encode(message):
    words = message.lower().translate(None, punctuation + ' ')
    code = ''.join(words).translate(encode_table)
    return ' '.join([code[i:i + group_len] for i in xrange(0, len(code), group_len)])

def decode(message):
    return message.replace(' ','').translate(decode_table)


