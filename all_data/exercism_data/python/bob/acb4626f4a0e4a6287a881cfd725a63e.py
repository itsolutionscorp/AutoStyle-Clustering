import re

class Bob:
   def hey(self,text):
      
      if re.match(r'^\s*$', text) :
         return 'Fine. Be that way!'
      else :
         if any( l.isalpha() for l in text ) and \
            all( l.isupper() for l in text if l.isalpha() ) :
            return 'Woah, chill out!'
         else :
            return 'Sure.' if text[-1] is '?' else 'Whatever.'
