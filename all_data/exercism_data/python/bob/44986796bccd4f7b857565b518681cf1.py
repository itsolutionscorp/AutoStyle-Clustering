#
# Skeleton file for the Python "Bob" exercise.
#
#-*- coding: UTF-8 -*-
def hey(what):
    whattest = what.strip(" \t\n\r")
    if whattest.isupper():
        return "Whoa, chill out!"
    elif whattest.endswith("?"):
        return "Sure."
    elif whattest == "":
        return "Fine. Be that way!"
    else: return "Whatever."
    
# elif what.replace("\t","").replace(" ","").replace("\n","") == "":
