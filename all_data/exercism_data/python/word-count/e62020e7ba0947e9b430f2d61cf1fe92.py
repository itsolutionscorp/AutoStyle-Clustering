#!/usr/bin/python
#############################################
#
# File Name : wordcount.py
#
# Last Modified : Mon 27 Oct 2014 08:45:55 PM PDT
#
#############################################

def word_count(line):
    line = line.split()
    words = {} 

    for word in line:
        if word not in words:
           # count = line.count(word)
           # print "%r: %r" % (word, count)
            words[word] = line.count(word)
    return words
