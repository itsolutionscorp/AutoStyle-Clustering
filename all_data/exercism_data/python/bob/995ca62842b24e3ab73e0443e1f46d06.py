# -*- coding: utf-8 -*-
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    qa=[(lambda:what.isupper(),'Whoa, chill out!'),
        (lambda:what.endswith('?'),'Sure.'),
        (lambda: not what or what.isspace(),'Fine. Be that way!')]
    for i in qa:
        #print i
        if i[0]():
            return(i[1])
            
    return'Whatever.'
    
