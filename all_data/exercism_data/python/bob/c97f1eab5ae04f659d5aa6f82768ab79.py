# Exercise 21
# Skeleton file for the Python 'Bob' exercise.
#
import unicodedata

def hey(what):
    what = ''.join(c for c in unicodedata.normalize('NFD',what)
        if unicodedata.category(c) != 'Mn')
    response = 'Whatever.'
    question = False
    yell = True
    empty = True
    numUpperCase = 0
    
    if len(what)==0:
        return 'Fine. Be that way!'
    
    for x in what:
        if x <= '~' and x >= '!':
            empty = False
            
        if x<= 'z' and x >= 'a': 
            yell = False
        
        if x<= 'Z' and x>= 'A':
            numUpperCase += 1;
        
        if x == '?':
            question = True
        
        if x >= '!':
            if x != '?':
                question = False
    
    if empty:
        return 'Fine. Be that way!'
    elif yell and numUpperCase != 0:
        return 'Whoa, chill out!'
    elif question:
        return 'Sure.'
        
    return response
