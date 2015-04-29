#!/usr/bin/python
import re

QUESTION = re.compile(".*\?$");
YELLING  = re.compile("[^a-z]+$");
IGNORING = re.compile("\s*$");
class Bob:
   def hey(self, message):
      if message == None or IGNORING.match( message ): 
         return 'Fine. Be that way!'

      if YELLING.match( message ):
         return 'Woah, chill out!'
      elif QUESTION.match( message ):
         return 'Sure.'
      else:
         return 'Whatever.'
