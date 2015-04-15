'''
Created on Sep 23, 2014

@author: W
'''

def hey(s):
    s = s.decode('utf-8')
    
    #IF ALLCAPS CHILL OUT
    if (s == s.upper() and s != s.lower()):
        return 'Whoa, chill out!'
        
    # if ? then sure
    if (s and s[-1]=='?'):
        return 'Sure.'
    
    # if empty, let it be
    if (s.strip()==''):
        return 'Fine. Be that way!'
    
    #else whatever
    return 'Whatever.'

                              
