#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    what = what.strip()


    if what == "":                          #empty String
        return "Fine. Be that way!"

    elif what.isupper():                    #"yelling" string
        return "Whoa, chill out!"

    elif what[-1] == "?":                   #not "yelling" question
        return "Sure."

    else:
        return "Whatever."                  #the rest, whatever ;) 

    
    
    
    
    



