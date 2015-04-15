#
# Skeleton file for the Python "Bob" exercise.
#
def is_number(s):
    """This helper function (stolen shamelessly from Stack Overflow user Daniel Goldberg), will help find out whether Bob is being yelled at"""
    try:
        float(s)
        return True
    except ValueError:
        return False


def hey(what):
    """Main function for the Bob chatbot"""
    #First, we'll do a check for a non-string, the empty string, or a long set of blank space
    if (type(what) is not str) or (what == '') or (len(what.split())==0):
        return "Fine. Be that way!"
    #If the input is a valid one, we'll check if Bob is being yelled at - assuming all caps means being yelled at.
    #Plan is to parse the input (using split) and count the the words that are all caps. 
    #If all the words in the sentenced are yelled, then Bob is being yelled at.
    words = what.split()
    yelledwords = 0
    wordsinsentence = len(words)
    for word in words:
        if is_number(word) or is_number(word[:-1]):#Numbers don't count as words, so reduce the total words in the sentence. This also checks for word+punctionations, ie '4,' or '5!'
            wordsinsentence -= 1 
        elif word.upper() == word:
            yelledwords += 1
    if (wordsinsentence is not 0) and (yelledwords == wordsinsentence):
        return 'Whoa, chill out!'
    #If the input wasn't yelling at Bob, we'll check to see if it asked a question
    if (what[-1] == '?'): #Note that the [-1] operator returns the last character of the input
        return "Sure."
    #Finally, if none of the above are the case, we'll return the default value
    return 'Whatever.'
