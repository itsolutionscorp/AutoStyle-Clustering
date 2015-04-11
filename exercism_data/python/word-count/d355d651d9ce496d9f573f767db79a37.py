__author__ = 'jeffmarkey'
import re

def word_count(words):
    diction={}
    words = words.lower()
    replace_list = ['!',':','&','%','^',',','@','$']

    for line in replace_list:
        words = words.replace(line,'')

    words = words.replace('  ',' ')
    word_list = words.split(' ')

    for line in word_list:
        if diction.has_key(line):
            diction[line]=diction[line]+1
        else:
            diction[line]=1

    return diction
