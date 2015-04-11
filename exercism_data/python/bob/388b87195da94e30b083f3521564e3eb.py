#!/usr/bin/python

class Bob(object):

  def hey(self, received):
    if received.isupper():
       return "Woah, chill out!"
    if received.endswith("?"):
       return "Sure."
    if not received or received.isspace():
       return "Fine. Be that way!"
    else:
       return "Whatever." 
    
