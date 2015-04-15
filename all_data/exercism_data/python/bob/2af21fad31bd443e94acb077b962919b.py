# -*- coding: utf-8 -*-
"""
Created on Tue Sep 23 23:52:52 2014

@author: laegrim
"""
import re

def hey(thing_said):
    '''
    Returns Bob's responses to various statements
    
    -If the thing said to bob was entirely upper case, return 
        Whoa, chill out!'
    -Else, if the thing said to bob ends in a question mark, return 
        'Sure.'
    -If there isn't any content, return 'Fine, Be that way!'
    -In any other case, return 'Whatever.'
    '''
    
    if thing_said.isupper():
        return 'Whoa, chill out!'
    elif len(re.sub('\s', '', thing_said)) == 0:
        return 'Fine. Be that way!' # \s matches all whitespace chars
    elif thing_said[-1] == '?':
        return 'Sure.'
    else:
        return 'Whatever.'
            
                
