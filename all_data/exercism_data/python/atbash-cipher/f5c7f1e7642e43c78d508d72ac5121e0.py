#!/usr/bin/env python3

import string
import re

plain = string.ascii_lowercase

def inset_whitespace(message):
    message_list = list(message)
    for i in range(len(message_list) // 5):
        index = i * 6 + 5
        message_list.insert(index, ' ')
    return ''.join(message_list)

def encode(message):
    message = re.sub(r'[^a-z0-9]', '', message.lower())
    message = message.translate(str.maketrans(plain, plain[::-1]))
    return inset_whitespace(message).strip()

def decode(message):
    message = re.sub(r'[\s]', '', message)
    message = message.translate(str.maketrans(plain[::-1], plain))
    return message
