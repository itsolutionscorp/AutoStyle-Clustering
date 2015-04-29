#!/usr/bin/env python
 # -*- coding: utf-8 -*-

def encode(text):
	__check_argument(text)
	encoded_text = ""
	for c in text:
		encoded_text += __get_transposition(c)
	
	return __add_a_space_every_5_characters(encoded_text)

def decode(text):
	__check_argument(text)
	decoded_text = ""
	for c in text:
		decoded_text+=__get_transposition(c)
	
	return decoded_text

def __check_argument(text):
	if not isinstance(text, "".__class__):
		raise ValueError("text must be a string")
	

def __add_a_space_every_5_characters(text):
	text_with_separation = ""
	for i in range(len(text)):
		text_with_separation += text[i] if i == 0 or i % 5 != 0 else " "+text[i]
	return text_with_separation

def __get_transposition(c):
	if c.isalnum():
		if c.isdecimal():
			return c
		return chr(25-(ord(c.lower())-97)+97)
	return ""
