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
    
    
def checkforresponse(strresp):
    #print strresp
    if strresp.isspace():
        return True
    if strresp:
        return False
    else:
        return True

def checkforquestion(strques):
    #print strques
    if (strques[-1] == u'?' ):
        return True
    return 0

def checkforyell(stryell):
    caps = len(filter(lambda x: x in string.ascii_uppercase, stryell))
    if caps > len(filter(lambda x: x in string.lowercase, stryell)):
        return True
    return 0


def hey(strin):
    #check for response
    if checkforresponse(strin):
       a = 'Fine. Be that way!'
    
    #Check for yelling
    elif checkforyell(strin):
        a = 'Whoa, chill out!'   

    #Check for properties
    elif checkforquestion(strin):
        a = 'Sure.'
           

        
    else:
        a = 'Whatever.'
    return a
def main():
    #Need to say "what" and look for input
    strin = whatuwant()
    print strin
    b = hey(strin)
    return b

# Standard boilerplate to call the main() function to begin
# the program.
if __name__ == '__main__':
	main()
