# This Python file uses the following encoding: utf-8

import re, sys, os

def hey(words):
	i = re.search('.$',words)
	j = re.search('[a-z]',words)
	k = re.search('[A-Z]',words)
	l = re.search('[0-9]',words)
	m = re.search('ñ',words)

	if words == '' or re.search('\t',words) != None:
		return('Fine. Be that way!')
	if words.isupper():
		return('Whoa, chill out!')
	if (j != None or l != None) and i.group(0) != '?':
		return('Whatever.')
	if i.group(0) == '?' and (j != None or l != None):
		return('Sure.')
	return('Whatever.')


