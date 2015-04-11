#!/usr/bin/env python
# -*- coding: utf-8 -*-

lowercase = map(chr, xrange( ord('a'), ord('z')+1 ))
uppercase = map(chr, xrange( ord('A'), ord('Z')+1 ))
lowercase.extend(['ä', 'ë', 'ï', 'ö', 'ü'])
uppercase.extend(['Ä', 'Ü'])


def hey(input):
	input = input.strip()
	if not input:
		return 'Fine. Be that way!'
	is_upper = [c in uppercase for c in input if c.lower() in lowercase]
	if is_upper and all(is_upper):
		return 'Woah, chill out!'
	if input.endswith('?'): # and any([c.lower() in lowercase for c in input]):
		return 'Sure.'
	return 'Whatever.'
	
hey('1, 2, 3')
