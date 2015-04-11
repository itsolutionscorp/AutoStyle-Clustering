#
# Skeleton file for the Python "Bob" exercise.
#
import re
import string

def hey(what):

    if re.search("^\s+$", what) or what == "":
        return 'Fine. Be that way!'
    split_what = what.split()
    #print "split what is", split_what
    if what.isupper():
        return 'Whoa, chill out!'

    if "?" in split_what[-1]:
        return "Sure."
    else:
       return 'Whatever.'
