# -*- coding: utf-8 -*-

""" Exercice Bob """

def hey(strng):
    # There is only 4 differents answers. 
    # The order of the tests is important.
    if strng.strip() == '':
        return 'Fine. Be that way!'
    elif strng.isupper():
        return 'Whoa, chill out!'
    elif strng[-1] == '?':
        return 'Sure.' 
    else:
        return 'Whatever.'
