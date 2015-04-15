# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 18:13:09 2014

"""

def hey(phrase):
    ''' Use this function to say something to bob

    Arguments:
    :param phrase: a sentence, question, group of words or jibberish
    :type phrase: str
    :returns: str
    '''
    phrase = phrase.strip()
    
    if phrase == "":                     # if you don't say anything
        return 'Fine. Be that way!'
    else:
        if phrase.isupper():             # if you are yelling
            return 'Whoa, chill out!'
        elif phrase[-1] == "?":          # If you're asking a question
            return "Sure."
        return 'Whatever.'
    
    
