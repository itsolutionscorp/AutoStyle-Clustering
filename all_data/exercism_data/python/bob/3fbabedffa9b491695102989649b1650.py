# -*- coding: utf-8 -*-
import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
   print what
   print re.search(r'[A-Z\u00c4\u00d6\u00dc]', unicode(what), re.U)
   print re.search(r'[a-z\u00e4\u00f6\u00fc]', unicode(what), re.U)
   print ""
   if re.search(r'^\s*$', what):
      return 'Fine. Be that way!'
   elif re.search(r'[A-Z\u00c4\u00d6\u00dc]', what, re.U) and not re.search(r'[a-z\u00e4\u00f6\u00fc]', what, re.U):
      return 'Whoa, chill out!'
   elif re.search(r'\?\s*$', what):
      return 'Sure.'

   return 'Whatever.'
