#!/usr/bin/env python

def hey(str_query=None):

	response_char_dict = {
		'?'		: 'Sure.',
		'!'		: 'Woah, chill out!',
		None	: 'Fine. Be that way!',
		''		: 'Whatever.',
	}

	str_query = str_query.strip(' \t\n\r')

	if 0 == len(str_query):
		mapped_response = None
	elif str_query == str_query.upper() and any(s.isalpha() for s in set(c for c in str_query)):
		mapped_response = '!'
	elif '?' == str_query[-1] in response_char_dict:
		mapped_response = str_query[-1]
	else:
		mapped_response = ''


	return response_char_dict[ mapped_response ]
