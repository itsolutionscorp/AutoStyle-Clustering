# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)
import re

def hey(txt):
    # if we say nothing
    if txt.strip() == "":
        return 'Fine. Be that way!'

    # if all letter-words are shouted, then we're shouting
    # we count number of words and number of shouts
    # if we have letter words, and they are all shouted, then we're shouting
    words = txt.split(' ')
    word_num = 0
    shout_num = 0
    for w in words:
        if re.match("[1-9]+", w):
            # non-letter words are counted as shouts
            shout_num += 1
            continue
        word_num += 1
        shout_num += 1 if w == w.upper() else 0
    if word_num > 0 and shout_num >= word_num:
        return 'Whoa, chill out!'

    # check if we asked a question
    if txt[-1] == '?':
        return 'Sure.'

    #default case
    return 'Whatever.'
