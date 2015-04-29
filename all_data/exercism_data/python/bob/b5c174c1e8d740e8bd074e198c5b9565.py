# -*- coding: utf-8 -*-
# By Nico Gevers (@gevious)

def hey(txt):
    # if we say nothing
    if txt.strip() == "":
        return 'Fine. Be that way!'

    # shouting if all words with letters are uppercase
    if txt.isupper():
        return 'Whoa, chill out!'

    # check if we asked a question
    if txt[-1] == '?':
        return 'Sure.'

    # If none of the above
    return 'Whatever.'
