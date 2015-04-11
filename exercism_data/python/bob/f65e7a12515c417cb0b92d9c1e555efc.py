'''
Created on Oct 7, 2014

@author: plaidfarmer
'''

def hey(text):
    if len(text.strip()) == 0:
        return 'Fine. Be that way!'
    elif text.isupper():
        return 'Whoa, chill out!'
    elif text.endswith('?'):
        return 'Sure.'
    return 'Whatever.'
