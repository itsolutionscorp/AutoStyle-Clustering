# -*- coding: utf-8 -*-

from __future__ import unicode_literals

class Bob():
   def hey(self, thing):
      if thing.strip() == '':                                  # Just whitespace
         return( 'Fine. Be that way!')
      elif thing == thing.upper() and thing != thing.lower():  # The != compare is to weed out no letters
         return( 'Whoa, chill out!' )
      elif thing[-1] == '?':                                   # Last character is '?' means question.
         return( 'Sure.')
      else:
         return( 'Whatever.' )                                 # Any uncaught input
