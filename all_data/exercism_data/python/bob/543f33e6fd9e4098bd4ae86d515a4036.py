#
# Skeleton file for the Python "Bob" exercise.

###str.endswith(suffix[, start[, end]])
#
def hey(what):
 what = what.strip()
 if not(what):
   return 'Fine. Be that way!'
 if what.isupper():
   return 'Whoa, chill out!'
 if what.endswith("?"):
   return 'Sure.'
 else:
     return 'Whatever.'
