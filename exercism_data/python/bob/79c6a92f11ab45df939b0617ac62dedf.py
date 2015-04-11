# -*- coding: utf-8 -*-

def hey(comment):
    if comment.isupper():
        return 'Woah, chill out!'
    elif comment[-1:] == '?':
        return 'Sure.'
    elif comment.isspace() or not comment:
        return 'Fine. Be that way!'
    else:
		return 'Whatever.'
