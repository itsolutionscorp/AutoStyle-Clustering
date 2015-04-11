#!/usr/bin/env python

import string

'''	AbC to zyx '''
alphabet = string.ascii_lowercase[::-1] + string.ascii_uppercase[::-1]
abash_alphabet = ''.join(((string.ascii_lowercase * 2)))

ATBASH_TABLE = string.maketrans(
	alphabet,
	abash_alphabet
	)

ENGLISH_TABLE = string.maketrans(
	abash_alphabet,
	alphabet
	)

deletechars = string.whitespace + string.punctuation

def encode(msg):
	msg = msg.translate(ATBASH_TABLE, deletechars)
	return add_spaces(msg)

def add_spaces(msg): # There has to be a better way
	tmp, i = '', 1
	for c in msg:
		tmp += c
		if i % 5 == 0:
			tmp += ' '
		i += 1
	msg = tmp
	return msg.strip()

def decode(msg):
	return msg.translate(ATBASH_TABLE, deletechars).strip()
