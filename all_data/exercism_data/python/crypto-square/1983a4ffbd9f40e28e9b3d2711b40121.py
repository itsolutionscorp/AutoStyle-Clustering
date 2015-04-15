from __future__ import division
import string
import math

def encode(phrase):
	punc = string.punctuation
	new_phrase = ''.join([letter for word in phrase.lower().split() for letter in word if letter not in punc])
	columns = int(math.ceil(math.sqrt(len(new_phrase))))
	rows = int(math.ceil(len(new_phrase)/columns)) if columns != 0 else 0
	phrase_list = [[] for row in range(rows)]

	#Build crypto square
	for row in range(rows):
		for col in range(columns):
			if columns*row + col < len(new_phrase):
				phrase_list[row].append(new_phrase[columns*row + col])
			else:
				phrase_list[row].append('')

	enc_phrase = ''
	row_count = 0
	col_count = 0
	letter_count = 0
	#Encrypt the phrase
	while col_count != columns:
		letter = phrase_list[row_count][col_count]
		if letter != '':
			enc_phrase += letter
			letter_count += 1

		if row_count != (rows-1):
			row_count += 1
		else:
			row_count = 0
			col_count += 1

		if letter_count == 5 and col_count != columns:
			enc_phrase += ' '
			letter_count = 0

	for o in phrase_list:
		print o

	return enc_phrase

def decode(enc_phrase):
	new_phrase = ''.join([letter for word in enc_phrase.split() for letter in word])
	columns = int(math.ceil(math.sqrt(len(new_phrase))))
	rows = int(math.ceil(len(new_phrase)/columns))
	phrase_list = [[] for row in range(rows)]

	for row in range(rows):
		for col in range(columns):
			if columns*row + col < len(new_phrase):
				phrase_list[row].append(new_phrase[columns*row + col])
			else:
				phrase_list[row].append('')

	phrase = ''
	row_count = 0
	col_count = 0
	letter_count = 0
	#Encrypt the phrase
	while col_count != columns:
		letter = phrase_list[row_count][col_count]
		if letter != '':
			phrase += letter

		if row_count != (rows-1):
			row_count += 1
		else:
			row_count = 0
			col_count += 1


	for o in phrase_list:
		print o

	return phrase


print decode(encode('tensioniswhoyouthinkyoushouldberelaxationiswhoyouare'))
