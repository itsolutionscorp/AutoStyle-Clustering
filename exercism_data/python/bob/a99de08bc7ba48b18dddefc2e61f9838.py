import re
#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
    #first test for emptyness. I've assumed empty strings are also 'saying nothing'
    if (what == None):
        return 'Fine. Be that way!'

    #the specification wasn't clear -- I'm assuming a string containing no alphanumerics is saying nothing.
 
    #So, we test for saying nothing by using a regex to substitute all non-alphanumerics away, and then seeing if the 
    #resultant string is the empty string ''.
    if (re.sub(r'\W+', r'', what) == ''):
        return 'Fine. Be that way!'

    #now test for shouting: I assume this means a string where all letters are uppercase. 
    
    #This is slightly tricky: one needs to see that the condition is not vacuous.
    #To do this, use a regex to strip out numbers, and search the result for alphanumerics.
    # (one has to be this convoluted because there seems to be no python regex special character for purely alphabetical characters)
    #one could, of course, craft a regex that accepts all alphanumerics that are not numeric, but this seems to me less readable.
    
    #The uppercase condition is handled by comparing the string to itself uppercased. 
    if ((re.search(r'\w', re.sub(r'\d+', r'', what), flags=re.UNICODE) != None) and (what.upper() == what)):
        return 'Whoa, chill out!'
    
    #now test for a question. This is defined as any string of characters ending in a question mark.
    if (what[len(what)-1] == '?'): 
        return 'Sure.'
    
    
    #all that is left is statements
    return 'Whatever.'
