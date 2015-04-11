#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
#Strips any trailling spaces from what string
    what = what.rstrip()
    
#Identifies strings that are all uppercase and returns appropriate response
    if what.isupper():
        return 'Whoa, chill out!'    

#Identifies strings that end in a question mark and returns appropriate response
    elif what.endswith("?") == True:
        return 'Sure.'

#Identifies empty strings and returns appropriate response
    elif not what:
        return 'Fine. Be that way!'

#Returns appropriate response for any other string
    else:
        return "Whatever."
