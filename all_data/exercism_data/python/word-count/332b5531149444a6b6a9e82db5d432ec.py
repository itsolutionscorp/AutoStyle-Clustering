#!/usr/bin/python

import re

# Grab the input
phrase = re.split(r'\W', raw_input('Tell me something. '))

# Create a frequency count
freq = [phrase.count(p) for p in phrase]

# Create the word/frequency pairs in a dictionary
dic = dict(zip(phrase, freq))

# Create a sorted word-frequency array from the dictionary
arr = [(dic[key], key) for key in dic]
arr.sort()

# Print the array
for a in arr: print a
