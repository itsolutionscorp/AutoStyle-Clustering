#
# Skeleton file for the Python "Bob" exercise.
#
import re

def hey(what):

    # check first for silence
    if re.findall("^[ \t\n]*$", what) != []:
        return "Fine. Be that way!"
    # check for caps before question
    # second condition rules out strings without letters
    elif what == what.upper() and not what == what.lower():
        return "Whoa, chill out!"
    # then question
    elif what.endswith("?"):
        return "Sure."
    else:
        return "Whatever."
        
