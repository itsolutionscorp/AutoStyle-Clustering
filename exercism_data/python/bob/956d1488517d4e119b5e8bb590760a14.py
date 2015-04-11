#!/usr/bin/python

class Bob(object):

  def hey(self, str1):
    if str1.isupper():
       return "Woah, chill out!"
    if str1.endswith("?"):
       return "Sure."
    if not str1 or '   ' in str1:
       return "Fine. Be that way!"
    else:
       return "Whatever." 
    
