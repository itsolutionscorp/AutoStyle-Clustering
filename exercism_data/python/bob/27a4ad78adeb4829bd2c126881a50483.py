#
# Skeleton file for the Python "Bob" exercise.
#
#from __future__ import unicode_literals

def hey(what):
    
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif what is None or what.strip()=="":
        return "Fine. Be that way!"
    else:
        return "Whatever."
