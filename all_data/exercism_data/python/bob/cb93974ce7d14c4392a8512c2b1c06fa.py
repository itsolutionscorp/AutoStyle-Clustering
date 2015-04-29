#
# Skeleton file for the Python "Bob" exercise.
#
#-*- coding: UTF-8 -*-
def hey(what):
    ans = 'Whatever.'
    whattest = what.strip(" \t\n\r")
    if what == "": return "Fine. Be that way!"
    if what.isupper():
        return "Whoa, chill out!"
    elif what.endswith("?"):
        return "Sure."
    elif what.split(" ") == 1: # and sum(1 for k in what if k.islower()) > 1:
        return "Whatever."
    elif whattest == "":
        return 'Fine. Be that way!'
    else: return 'Whatever.'
    
# elif what.replace("\t","").replace(" ","").replace("\n","") == '':
