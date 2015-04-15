#!/usr/bin/env python
# Skeleton file for the Python "Bob" exercise
# -*- coding: utf-8 -*-
from __future__ import unicode_literals
import os
import sys
import string


def whatuwant():
    strin = raw_input('what?:\n')
    uni = strin.decode('utf-8')
    #print uni
    return(uni)
    
    
def checkfornoresponse(strresp):
    print 'checkfornoresponse called\n'    
    return not len(strresp) > 0
    return()


def checkforquestion(strques):
    print 'checkforquestioncalled\n'
    return strques.endswith('?') 
        

def checkforyell(stryell):
    print 'checkforyell called\n'
    caps = len(filter(lambda x: x in string.uppercase, stryell))
    if caps > len(filter(lambda x: x in string.lowercase, stryell)):
        return True


def hey(rawin):
    print 'hey called\n'
    strin = rawin.strip()
    #check for response
    if checkfornoresponse(strin):
       a = 'Fine. Be that way!'
    
    #Check for yelling
    elif checkforyell(strin):
        a = 'Whoa, chill out!'   

    #Check for question mark
    elif checkforquestion(strin):
        a = 'Sure.'
        
    else:
        print 'else statement hit, assigning a to whatever '
        a = 'Whatever.'
    return(a)


def main():
    #Need to say "what" and look for input
    strin = whatuwant()
    print strin
    b = hey(strin)
    print b
    return b

# Standard boilerplate to call the main() function to begin
# the program.
if __name__ == '__main__':
	main()
