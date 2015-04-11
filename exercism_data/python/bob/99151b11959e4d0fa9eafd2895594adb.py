# -*- coding: utf-8 -*-
from __future__ import unicode_literals


def hey(stuff):
    if stuff.isupper():
        return 'Whoa, chill out!'
    elif stuff != '' and stuff[-1] == '?' :
        return 'Sure.'
    elif stuff == '' or stuff.isspace():
        return 'Fine. Be that way!'
    else:
        return "Whatever."

# just a few notes to identify patterns in the tests.
#    if stuff in ("Tom-ay-to, tom-aaaah-to.","Let's go make out behind the gym!", "It's OK if you don't want to go to the DMV.","1, 2, 3","ÜMLäÜTS!","Ending with ? means a question.","         hmmmmmmm..."):
#        return 'Whatever.'
#    elif stuff in ("WATCH OUT!", "WHAT THE HELL WERE YOU THINKING?", "1, 2, 3 GO!", "ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!","ÜMLÄÜTS!","I HATE YOU"):
#        return 'Whoa, chill out!'
#    elif stuff in ("Does this cryogenic chamber make me look fat?" ,"You are, what, like 15?", "4?","Wait! Hang on. Are you going to be OK?"):
#        return 'Sure.'
#    elif stuff in ('',"    \t"):
#        return 'Fine. Be that way!'
