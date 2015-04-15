#!/usr/bin/env python

import string

'''	AbC to zyx '''
alphabet = string.ascii_lowercase[::-1] + string.ascii_uppercase[::-1]
abash_alphabet = ''.join(((string.ascii_lowercase * 2)))

ATBASH_TABLE = string.maketrans(
	alphabet,
	abash_alphabet
	)

deletechars = string.whitespace + string.punctuation

def encode(msg):
	msg = msg.translate(ATBASH_TABLE, deletechars)
	return add_spaces(msg)

def add_spaces(msg): # There is a better way!
	return " ".join([msg[i:i+5] 
					for i in range(0, len(msg), 5)])

def decode(msg):
	return msg.translate(ATBASH_TABLE, deletechars).strip()
