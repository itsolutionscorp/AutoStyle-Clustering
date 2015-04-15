#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):

    #setting blank counter for blank input
    blankCount=0
    #taking len of word to compare number of blank to total length
    whatLength = len(what)
    #for loop to iterate through input
    for letter in what:
        if letter == " ":
            #add 1 to blank count
            blankCount = blankCount + 1
        #Factor in newlines and tabs
        elif letter == "\n": 
            blankCount = blankCount + 1
        elif letter == "\t": 
            blankCount = blankCount + 1
    #comparing blank letters to length of string
    if blankCount == whatLength:
        return "Fine. Be that way!"
    #if all uppercase, it is yelling      
    if what.isupper() == True:
        return "Whoa, chill out!"
    #if it is question
    elif what.endswith("?"):
        return "Sure."
    
    else:
        return "Whatever."
