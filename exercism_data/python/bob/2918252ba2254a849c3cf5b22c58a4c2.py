#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    what = what.rstrip() # remove trailing whitespace
    
    if what == '': # any argument that was all whitespace is now empty
      return "Fine. Be that way!"
    
    if what.isupper():
      return "Whoa, chill out!"
      
    if what[-1] == '?':
      return "Sure."
    
    return "Whatever."
