#
# Second implementation of "Bob" exercise.
#
def hey(what):

    # strips spaces
    what = what.strip()
    
    # answers if there are no alphanumeric characters in argument
    if not alphanumber(what):           
        return 'Fine. Be that way!'

    # answers if argument is in all caps
    elif what.isupper():                 
        return 'Whoa, chill out!'

    # answers if last character is question mark
    elif what.endswith('?'):
        return 'Sure.'

    else:
        return 'Whatever.'


# function to check if at least one character on argument is alphanumeric
def alphanumber(text):

    for letter in text:
        if letter.isalpha() or letter.isdigit():
            return True

    return False
