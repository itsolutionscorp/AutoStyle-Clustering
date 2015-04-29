# -*- coding: utf-8 -*-

class Bob():
   def hey(self, prompt):
      strippedprompt=prompt.strip()
      if not strippedprompt:
         return( 'Fine. Be that way!')
      elif strippedprompt.isupper():
         return( 'Whoa, chill out!' )
      elif strippedprompt[-1] == '?':
         return( 'Sure.')
      else:
         return( 'Whatever.' )
