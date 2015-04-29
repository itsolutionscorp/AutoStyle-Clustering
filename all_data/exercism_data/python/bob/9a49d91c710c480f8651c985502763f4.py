# -*- coding: utf-8 -*-
"""
Created on Wed Sep 24 09:56:55 2014

@author: Blair
"""


def hey(text):
    text = text.strip()
#    print text
    if len(text) < 1:
        return "Fine. Be that way!"
    elif text.isupper():
        return "Whoa, chill out!"
    elif text.endswith("?"):
        return "Sure."            
    else:
        return "Whatever."
        
#print hey('Tom-ay-to, tom-aaaah-to.') #whatever
#print hey('WATCH OUT!') #chillout
#print hey('Does this cryogenic chamber make me look fat?') #sure
#print hey('You are, what, like 15?') #sure
#print hey("Let's go make out behind the gym!") #whatever
#print hey("It's OK if you don't want to go to the DMV.") #whatever
#print hey('WHAT THE HELL WERE YOU THINKING?') #chillout 
#print hey('1, 2, 3 GO!') #chillout
#print hey('1, 2, 3') #whatever
#print hey('4?') #sure
#print hey('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!') #chillout
#print hey(u'ÜMLÄÜTS!') #chillout
#print hey(u'ÜMLäÜTS!') #whatever
#print hey('I HATE YOU') #chillout
#print hey('Ending with ? means a question.') #whatever
#print hey("Wait! Hang on. Are you going to be OK?") #sure
#print hey('') #Fine be that way
#print hey('    \t') #Fine be that way
#print hey('         hmmmmmmm...') #Whatever
