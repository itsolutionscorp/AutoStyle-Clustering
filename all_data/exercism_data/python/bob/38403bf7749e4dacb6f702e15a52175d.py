#
# Skeleton file for the Python "Bob" exercise.
#
#-*- coding: UTF-8 -*-
def hey(what):
    ans = 'Whatever.'
    if what == "": return "Fine. Be that way!"
    whatUmlauted = sum(1 for c in what if c.isupper())
    if "?" == what[-1] and what.isupper()==False:
        ans = "Sure."
    elif what.isupper():
    	ans = "Whoa, chill out!"
    elif what.split(" ") == 1 and sum(1 for k in what if k.islower()) > 1:
    	ans = "Whatever."
    elif what.replace("\t","").replace(" ","").replace("\n","") == '':
        ans = 'Fine. Be that way!'
    else: ans = 'Whatever.'
    return ans
