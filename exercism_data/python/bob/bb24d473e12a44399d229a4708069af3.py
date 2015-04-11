#
# First implementation of "Bob" exercise.
#
def hey(what):

    # answers if there are no alphanumeric characters in argument
    if not alphanumber(what):           
        return 'Fine. Be that way!'

    # answers if argument is in all caps
    elif what.isupper():                 
        return 'Whoa, chill out!'

    # answers if last valid (non-space) character is a question mark
    elif last_character(what) == '?':
        return 'Sure.'

    else:
        return 'Whatever.'


# function to return the argument's last valid (non-space) character
def last_character(text):
    
    # inverts string
    invtext = []
    
    for i in range(0, len(text)):
        invtext.append(text[-1-i])

    # returns first non-space character of inverted string
    for i in range(0, len(invtext)):
        if not invtext[i].isspace():
            return invtext[i]


# function to check if at least one character on argument is alphanumeric
def alphanumber(text):

    for i in range(len(text) - 1):
        if text[i].isalpha() or text[i].isdigit():
            return True

    return False
