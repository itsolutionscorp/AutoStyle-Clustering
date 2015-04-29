#-*- coding: utf-8 -*-

def hey(sentence):
    if sentence.isupper():          # yelling
        return 'Whoa, chill out!'
    elif sentence.endswith('?'):    # question
        return 'Sure.'
    elif sentence.strip() == '':    # nothing
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
