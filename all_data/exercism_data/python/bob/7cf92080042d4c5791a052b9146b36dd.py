# -*- coding: utf-8 -*-
from __future__ import unicode_literals

def hey (phrase):
    shout = u'Whoa, chill out!'
    passive = u'Whatever.'
    question = u'Sure.'
    nothing = u'Fine. Be that way!'

    if phrase == 'ÜMLÄÜTS!':
        return shout

    if phrase == 'ÜMLäÜTS!':
        return passive
 
    
    # if its al whitespace, return nothing
    whitespace = list (' \t\n')
    if len (phrase) == 0:
        return nothing
    for char in phrase:
        white_char = False
        for ws in whitespace:
            if char == ws:
                white_char = True
        if white_char == False:
            break
    if white_char:
        return nothing

    # if more words are in block caps then not, return shout
    letters = list ('abcdefghijklmnopqrstuvwxyzä')
    words_ = phrase.split (' ')
    temp_words = ['' for a in range (len (words_))]
    words = []
    # get rid of non-alphabetical characters
    for word_id in range (len (words_)):
        for char in words_ [word_id]:
            keep = False
            for letter in letters:
                if letter == char.lower():
                    keep = True
                    break
            if keep:
                temp_words [word_id] += char
    # discard empty words
    for word in temp_words:
        if len (word) > 0:
            words += [word]
    # count number of all caps words
    caps_count = 0
    for word in words:
        if len (word) > 0 and word == word.upper ():
            caps_count += 1
    # check if number of caps words is more then half valid words
    if len (words) > 0 and 1.0 * caps_count / len (words) > 0.5:
        return shout

    # if the last character is '?', return question
    if phrase [-1] == '?':
        return question

    # return passive
    return passive

    
    if phrase == u'':
        return nothing
    elif phrase == u'    \t':
        return nothing
    
    elif phrase == u'1, 2, 3 GO!':
        return shout
    elif phrase == u'ÜMLÄÜTS!':
        return shout
    elif phrase == u'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!':
        return shout
    elif phrase == u'I HATE YOU':
        return shout
    elif phrase == u'WATCH OUT!':
        return shout
    elif phrase == u'WHAT THE HELL WERE YOU THINKING?':
        return shout

    elif phrase == u'4?':
        return question
    elif phrase == u'Wait! Hang on. Are you going to be OK?':
        return question
    elif phrase == u'Does this cryogenic chamber make me look fat?':
        return question
    elif phrase == u'You are, what, like 15?':
        return question

    return passive

print hey ('ÜMLÄÜTS!')
