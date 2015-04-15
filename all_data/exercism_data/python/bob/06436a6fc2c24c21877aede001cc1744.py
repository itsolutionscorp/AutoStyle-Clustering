# -*- coding: utf-8 -*-
from __future__ import unicode_literals
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #print ' '
    answer='Whatever.' #default
    what=what.strip() #strip whitespace
    #print what
    if (what.lower()==what): #check to see if there are any upper case letters
        allCAPS=False
    else:
        allCAPS=True
    for i in range(0,len(what)):
        #print what[i],i,ord(what[i]),allCAPS
        if what[i]=='?': #questions end in question marks
            answer= 'Sure.'
        elif what[i]=='.':#in case there is an extraneous question mark 
            answer= 'Whatever.'
        elif (what[i].isalpha and what[i].islower()): #if any lowercase, not shouting
            allCAPS=False
    if len(what)==0: #if nothing
        answer= 'Fine. Be that way!'
    elif allCAPS: #if allCAPS then shouting
        answer= 'Whoa, chill out!'
    #print allCAPS
    #print answer
    return answer

if __name__ == "__main__":# my test cases
    hey('jomama!')
    hey('what?')
    hey('')
    hey('hi.')
    hey('ÜMLäÜTS!')
    hey('ÜMLÄÜTS!')
    hey('HEY YOU')
    hey('4?')
