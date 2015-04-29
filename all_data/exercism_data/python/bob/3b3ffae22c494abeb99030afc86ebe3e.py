'''
Created on Sep 23, 2014

@author: Adam Smith
'''

def hey(statement):
    if not statement.strip():
        # empty statement responds with 'Fine. Be that way!'
        return 'Fine. Be that way!'
    if statement.upper() == statement and not all(ch in "0123456789,.!? " for ch in statement):
        # All caps responds with 'Whoa, chill out!'
        return 'Whoa, chill out!'
    if statement[-1] == "?":
        # question statement responds with 'Sure.'
        return 'Sure.'
    else:
        # all other statements respond with 'Whatever.'
        return 'Whatever.'
