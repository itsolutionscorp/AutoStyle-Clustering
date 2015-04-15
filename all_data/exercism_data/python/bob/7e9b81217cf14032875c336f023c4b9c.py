#!/usr/bin/python

def hey(str):
    if str.isupper():
        return 'Woah, chill out!'
    elif str.endswith("?"):
        return 'Sure.'
    elif str.isspace() or len(str)==0:
        return 'Fine. Be that way!'
    else:
        return 'Whatever.'
