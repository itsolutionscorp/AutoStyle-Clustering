#
# Second implementation of "Bob" exercise.
#
def hey(what):

    what = what.strip()
    
    # answers if there are no alphanumeric characters in argument
    if not alphanumber(what):           
        return 'Fine. Be that way!'

    # answers if argument is in all caps
    elif what.isupper():                 
        return 'Whoa, chill out!'

    # answers if last valid (non-space) character is a question mark
    elif what[-1] == '?':
        return 'Sure.'

    else:
        return 'Whatever.'


# function to check if at least one character on argument is alphanumeric
def alphanumber(text):

    for i in range(len(text) - 1):
        if text[i].isalpha() or text[i].isdigit():
            return True

    return False
