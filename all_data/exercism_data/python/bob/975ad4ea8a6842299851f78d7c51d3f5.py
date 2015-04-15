# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#

#Bob answers 'Sure.' if you ask him a question.
#
#He answers 'Whoa, chill out!' if you yell at him.
#
#He says 'Fine. Be that way!' if you address him without actually saying
#anything.
#
#He answers 'Whatever.' to anything else.

def hey(what):
    strpstr = unicode.strip(what)
    
    if unicode.isupper(strpstr) or (len(strpstr) != 0 and strpstr[-1] == '!' and unicode.isupper(strpstr)):
        phr = 'Whoa, chill out!'
    elif len(strpstr) != 0 and strpstr[-1] == '?':
        phr = 'Sure.'
    elif strpstr == '':
        phr = 'Fine. Be that way!'
    else:
        phr = 'Whatever.'
        
    return phr
        
    

